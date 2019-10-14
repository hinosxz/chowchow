package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.show.controllers.ShowDTO;
import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.service.ShowsService;
import com.centralesupelec.chowchow.showRating.controllers.ShowRatingDTO;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller
public class UsersController {

    private final UsersServiceImpl usersServiceImpl;
    private final ShowsService showsService;

    private final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    public UsersController(
            UsersServiceImpl usersServiceImpl,
            ShowsService showsService
    ) {
        this.usersServiceImpl = usersServiceImpl;
        this.showsService = showsService;
    }

    public boolean createUser(UserDTO userDTO) {
        Optional<UserEntity> maybeUser = this.usersServiceImpl.getUserByUsername(userDTO.getUsername());
        if (maybeUser.isPresent()){
            return false;
        }
        usersServiceImpl.saveUser(UserDTO.toEntity(userDTO));
        return true;
    }

    public Optional<UserDTO> getUserById(Long id){
        return this.usersServiceImpl.getUserById(id).map(UserDTO::fromEntity);
    }

    public Set<ShowDTO> getLikedShows(Long id){
        Optional<UserDTO> maybeUserDTO = this.getUserById(id);
        if (!maybeUserDTO.isPresent()){
            return null;
        }
        Set<ShowRatingDTO> showRatings = maybeUserDTO.get().getLikedShows();
        Set<CompletableFuture<ShowDTO>> showSet = new HashSet<>();

        Set<CompletableFuture<Optional<ShowDTO>>> maybeShowDTOPromises = showRatings.stream()
                .map(showRating -> showRating.getShowId())
                .map(showRatingId -> this.showsService.getShowById(showRatingId))
                .map(maybeShowEntityPromise -> maybeShowEntityPromise
                        .thenApply(maybeShowEntity -> maybeShowEntity
                                .map(showEntity -> ShowDTO.fromEntity(showEntity))))
                .collect(Collectors.toSet());

        Set<ShowDTO> showDTOS = maybeShowDTOPromises.stream()
                .map(maybeShowDTOPromise -> maybeShowDTOPromise.join())
                .filter(maybeShowDTO -> maybeShowDTO.isPresent())
                .map(maybeShowDTO -> maybeShowDTO.get())
                .collect(Collectors.toSet());

        return showDTOS;
    }

    public boolean likeShow(ShowRatingDTO showRatingDTO, Long userId) {
        Optional<UserEntity> maybeUser = this.usersServiceImpl.getUserById(userId);
        if (!maybeUser.isPresent()) {
            logger.warn("Unsuccessful attempts to find user with id {}", userId);
            return false;
        }
        Optional<ShowEntity> maybeShow = showsService.getShowById(showRatingDTO.getShowId()).join();
        if (!maybeShow.isPresent()){
            logger.warn("Unsuccessful attempts to find user with id {}", userId);
            return false;
        }
        UserEntity user = maybeUser.get();
        ShowEntity show = maybeShow.get();
        user.likeShow(showRatingDTO.getMark(), show);
        this.usersServiceImpl.saveUser(user);
        return true;
    }

    public boolean unlikeShow(Long showId, Long userId) {
        Optional<UserEntity> maybeUserDTO = this.usersServiceImpl.getUserById(userId);
        if(!maybeUserDTO.isPresent()) {
            logger.warn("Unsuccessful attempts to find user with id {}", userId);
            return false;
        }
        UserEntity user = maybeUserDTO.get();
        user.unlikeShow(showId);
        this.usersServiceImpl.saveUser(user);
        return true;
    }

}
