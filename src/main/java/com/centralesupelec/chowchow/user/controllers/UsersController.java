package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
import com.centralesupelec.chowchow.likes.controllers.LikeDTO;
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

  public Optional<UserDTO> getUserById(Integer id) {
    return this.usersService.getUserById(id).map(UserDTO::fromEntity);
  }

  public List<LikeDTO> getLikedShows(Integer id) {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(id);
    if (!maybeUser.isPresent()) {
      return null;
    }
    List<CompletableFuture<LikeDTO>> likeDTOPromises =
        maybeUser.get().getLikedShows().stream()
            .map(
                like ->
                    this.searchService
                        .findShowById(like.getShowId())
                        .thenApply(tmdbShowDTO -> new LikeDTO(like.getMark(), tmdbShowDTO)))
            .collect(Collectors.toList());

    return likeDTOPromises.stream()
        .map(likedDTOPromise -> likedDTOPromise.join())
        .collect(Collectors.toList());
  }

  public boolean likeShow(LikeDTO likeDTO, Integer userId) {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      LOGGER.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUser.get();
    boolean success = user.likeShow(likeDTO.getMark(), likeDTO.getTmdbShowDTO().getId());
    this.usersService.saveUser(user);
    return success;
  }

  public boolean updateMark(LikeDTO likeDTO, Integer userId) {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      LOGGER.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUser.get();
    boolean success = user.updateMark(likeDTO.getMark(), likeDTO.getTmdbShowDTO().getId());
    this.usersService.saveUser(user);
    return success;
  }

  public boolean unlikeShow(Integer showId, Integer userId) {
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

  public boolean getUpcomingEpisodesForUser(Integer userId) {
    return true;
  }
}
