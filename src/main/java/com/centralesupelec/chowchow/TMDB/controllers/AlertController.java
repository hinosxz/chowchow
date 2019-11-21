package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.controllers.useralerts.UserAlertsManager;
import com.centralesupelec.chowchow.TMDB.service.AlertService;
import com.centralesupelec.chowchow.likes.domain.Like;
import com.centralesupelec.chowchow.likes.domain.Mark;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class AlertController {

  private final Logger logger = LoggerFactory.getLogger(AlertController.class);

  private final AlertService alertService;
  private final UsersService usersService;

  @Autowired
  public AlertController(AlertService alertService, UsersService usersService) {
    this.alertService = alertService;
    this.usersService = usersService;
  }

  public List<AlertDTO> getAlertsForUser(Integer userId) {
    Optional<UserEntity> maybeUserDTO = this.usersService.getUserById(userId);
    if (!maybeUserDTO.isPresent()) {
      logger.warn("Unsuccessful attempts to find user with id {}", userId);
      return new ArrayList<>();
    }
    UserAlertsManager userAlertsManager = new UserAlertsManager();
    List<Like> likedShows = maybeUserDTO.get().getLikedShows();
    this.getAlerts(likedShows);
  }

  private List<AlertDTO> getAlerts(List<Like> likedShows) throws HttpStatusCodeException {
    List<Pair<CompletableFuture<AlertDTO>, Mark>> maybePairs =
        likedShows.stream()
            .map(
                likedShow ->
                    new Pair<>(
                        this.alertService.findAlertByShowId(likedShow.getShowId()),
                        likedShow.getMark()))
            .collect(Collectors.toList());
    return this.alertService.findAlertsByShowIds().stream()
        .filter(
            alert ->
                alert.getNextEpisodeToAir() != null
                    && this.isEpisodeSoon(alert.getNextEpisodeToAir()))
        .collect(Collectors.toList());
  }
}
