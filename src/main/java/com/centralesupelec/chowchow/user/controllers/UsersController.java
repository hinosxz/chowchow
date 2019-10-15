package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.TMDB.service.AlertService;
import com.centralesupelec.chowchow.likes.controllers.ShowRatingDTO;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersServiceImpl;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {

  private final UsersServiceImpl usersServiceImpl;
  private final AlertService alertService;

  private final Logger logger = LoggerFactory.getLogger(UsersController.class);

  @Autowired
  public UsersController(UsersServiceImpl usersServiceImpl, AlertService alertService) {
    this.usersServiceImpl = usersServiceImpl;
    this.alertService = alertService;
  }

  public boolean createUser(UserDTO userDTO) {
    Optional<UserEntity> maybeUser = this.usersServiceImpl.getUserByUsername(userDTO.getUsername());
    if (maybeUser.isPresent()) {
      return false;
    }
    usersServiceImpl.saveUser(UserDTO.toEntity(userDTO));
    return true;
  }

  public Optional<UserDTO> getUserById(Long id) {
    return this.usersServiceImpl.getUserById(id).map(UserDTO::fromEntity);
  }

  public Set<Long> getLikedShowIds(Long id) {
    Optional<UserDTO> maybeUserDTO = this.getUserById(id);
    if (!maybeUserDTO.isPresent()) {
      return null;
    }
    Set<ShowRatingDTO> showRatingDTOs = maybeUserDTO.get().getLikedShows();

    return showRatingDTOs.stream()
        .map(showRatingDTO -> showRatingDTO.getShowId())
        .collect(Collectors.toSet());
  }

  public boolean likeShow(ShowRatingDTO showRatingDTO, Long userId) {
    Optional<UserEntity> maybeUser = this.usersServiceImpl.getUserById(userId);
    if (!maybeUser.isPresent()) {
      logger.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUser.get();
    user.likeShow(showRatingDTO.getMark(), showRatingDTO.getShowId());
    this.usersServiceImpl.saveUser(user);
    return true;
  }

  public boolean unlikeShow(Long showId, Long userId) {
    Optional<UserEntity> maybeUserDTO = this.usersServiceImpl.getUserById(userId);
    if (!maybeUserDTO.isPresent()) {
      logger.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUserDTO.get();
    user.unlikeShow(showId);
    this.usersServiceImpl.saveUser(user);
    return true;
  }

  public boolean getUpcomingEpisodesForUser(Long userId) {
    return true;
  }
}
