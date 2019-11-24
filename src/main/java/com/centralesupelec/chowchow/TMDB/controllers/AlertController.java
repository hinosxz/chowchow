package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.controllers.user_alerts.UserAlertsManager;
import com.centralesupelec.chowchow.TMDB.service.AlertService;
import com.centralesupelec.chowchow.likes.domain.Mark;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UserService;
import com.centralesupelec.chowchow.user.service.UserServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class AlertController {

  private final Logger logger = LoggerFactory.getLogger(AlertController.class);

  private final AlertService alertService;
  private final UserService userService;

  @Autowired
  public AlertController(AlertService alertService, UserServiceImpl userServiceImpl) {
    this.alertService = alertService;
    this.userService = userServiceImpl;
  }

  public List<AlertDTO> getAlertsForUserId(Integer userId) {
    Optional<UserEntity> maybeUser = this.userService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      logger.warn("Unsuccessful attempts to find user with id {}", userId);
      return new ArrayList<>();
    }
    UserEntity user = maybeUser.get();
    return this.getAlertsForUser(user);
  }

  private List<AlertDTO> getAlertsForUser(UserEntity user) throws HttpStatusCodeException {
    HashMap<Integer, Mark> marksMap = new HashMap<>();
    user.getLikedShows()
        .forEach(likedShow -> marksMap.put(likedShow.getShowId(), likedShow.getMark()));

    HashMap<AlertDTO, Mark> alertsMap = new HashMap<>();
    this.alertService
        .findAlertsByShowIds(
            user.getLikedShows().stream()
                .map(likedShow -> likedShow.getShowId())
                .collect(Collectors.toList()))
        .forEach(
            alertDTO -> alertsMap.put(alertDTO, marksMap.getOrDefault(alertDTO.getShowId(), null)));

    UserAlertsManager userAlertsManager = new UserAlertsManager(alertsMap);
    return userAlertsManager.getAlerts();
  }
}
