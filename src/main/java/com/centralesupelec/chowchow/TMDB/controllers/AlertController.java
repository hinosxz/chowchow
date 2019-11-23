package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.service.AlertService;
import com.centralesupelec.chowchow.TMDB.service.AlertServiceImpl;
import com.centralesupelec.chowchow.likes.domain.Like;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UserService;
import com.centralesupelec.chowchow.user.service.UserServiceImpl;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

  private final int ALERT_THRESHOLD = 3;
  private final Logger logger = LoggerFactory.getLogger(AlertController.class);

  private final AlertService alertService;
  private final UserService userService;

  @Autowired
  public AlertController(AlertServiceImpl alertServiceImpl, UserServiceImpl userServiceImpl) {
    this.alertService = alertServiceImpl;
    this.userService = userServiceImpl;
  }

  private boolean isEpisodeSoon(TMDBEpisodeDTO episode) {
    LocalDate now = LocalDate.now();
    return now.until(episode.getAirDate(), ChronoUnit.DAYS) < ALERT_THRESHOLD;
  }

  public List<AlertDTO> getAlertsForUser(Integer userId) {
    Optional<UserEntity> maybeUserDTO = this.userService.getUserById(userId);
    if (!maybeUserDTO.isPresent()) {
      logger.warn("Unsuccessful attempts to find user with id {}", userId);
      return new ArrayList<>();
    }
    List<Integer> likedShowIds =
        maybeUserDTO.get().getLikedShows().stream()
            .map(Like::getShowId)
            .collect(Collectors.toList());
    return this.getAlerts(likedShowIds);
  }

  private List<AlertDTO> getAlerts(List<Integer> tmdbIds) throws HttpStatusCodeException {
    return this.alertService.findAlertsByShowIds(tmdbIds).stream()
        .filter(
            alert ->
                alert.getNextEpisodeToAir() != null
                    && this.isEpisodeSoon(alert.getNextEpisodeToAir()))
        .collect(Collectors.toList());
  }
}
