package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
import com.centralesupelec.chowchow.likes.controllers.ShowRatingDTO;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {
  private final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

  private final UsersService usersService;
  private final SearchService searchService;

  @Autowired
  public UsersController(UsersService usersService, SearchService searchService) {
    this.usersService = usersService;
    this.searchService = searchService;
  }

  public boolean createUser(UserDTO userDTO) {
    Optional<UserEntity> maybeUser = this.usersService.getUserByUsername(userDTO.getUsername());
    if (maybeUser.isPresent()) {
      return false;
    }
    usersService.saveUser(UserDTO.toEntity(userDTO));
    return true;
  }

  public Optional<UserDTO> getUserById(Long id) {
    return this.usersService.getUserById(id).map(UserDTO::fromEntity);
  }

  public List<LikedShowDTO> getLikedShows(Long id) {
    Optional<UserDTO> maybeUserDTO = this.getUserById(id);
    if (!maybeUserDTO.isPresent()) {
      return null;
    }
    List<CompletableFuture<LikedShowDTO>> likedShowsDTOPromises =
        maybeUserDTO.get().getLikedShows().stream()
            .map(
                showRatingDTO ->
                    this.searchService
                        .findShowById(showRatingDTO.getShowId())
                        .thenApply(
                            tmdbShowDTO -> new LikedShowDTO(showRatingDTO.getMark(), tmdbShowDTO)))
            .collect(Collectors.toList());

    return likedShowsDTOPromises.stream()
        .map(likedShowsDTOPromise -> likedShowsDTOPromise.join())
        .collect(Collectors.toList());
  }

  public boolean likeShow(ShowRatingDTO showRatingDTO, Long userId) {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      LOGGER.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUser.get();
    user.likeShow(showRatingDTO.getMark(), showRatingDTO.getShowId());
    this.usersService.saveUser(user);
    return true;
  }

  public boolean unlikeShow(Long showId, Long userId) {
    Optional<UserEntity> maybeUserDTO = this.usersService.getUserById(userId);
    if (!maybeUserDTO.isPresent()) {
      LOGGER.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUserDTO.get();
    user.unlikeShow(showId);
    this.usersService.saveUser(user);
    return true;
  }

  public boolean getUpcomingEpisodesForUser(Long userId) {
    return true;
  }
}
