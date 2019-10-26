package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.service.AlertService;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
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
  private final UsersService usersService;

  @Autowired
  public AlertController(AlertService alertService, UsersService usersService) {
    this.alertService = alertService;
    this.usersService = usersService;
  }

  private boolean isEpisodeSoon(TMDBEpisodeDTO episode) {
    LocalDate now = LocalDate.now();
    return now.until(episode.getAirDate(), ChronoUnit.DAYS) < ALERT_THRESHOLD;
  }

  public List<TMDBEpisodeDTO> getUpcomingEpisodesForUser(Integer userId) {
    Optional<UserEntity> maybeUserDTO = this.usersService.getUserById(userId);
    if (!maybeUserDTO.isPresent()) {
      logger.warn("Unsuccessful attempts to find user with id {}", userId);
      return new ArrayList<>();
    }
    List<Integer> likedShowIds =
        maybeUserDTO.get().getLikedShows().stream()
            .map(likedShow -> likedShow.getShowId())
            .collect(Collectors.toList());
    return this.getUpcomingEpisodes(likedShowIds);
  }

  private List<TMDBEpisodeDTO> getUpcomingEpisodes(List<Integer> tmdbIds)
      throws HttpStatusCodeException {
    return this.alertService.findNextEpisodesByShowIds(tmdbIds).stream()
        .filter(episode -> this.isEpisodeSoon(episode))
        .collect(Collectors.toList());
  }
}
