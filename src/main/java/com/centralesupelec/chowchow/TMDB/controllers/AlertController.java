package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.service.ShowsService;
import com.centralesupelec.chowchow.TMDB.service.AlertService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.Arrays;

@Controller
public class AlertController {

    private final Logger logger = LoggerFactory.getLogger(AlertController.class);

    private final int ALERT_THRESHOLD = 3;

    private final AlertService alertService;
    private final ShowsService showsService;

    @Autowired
    public AlertController(AlertService alertService, ShowsService showsService) {
        this.alertService = alertService;
        this.showsService = showsService;
    }

    private boolean isEpisodeSoon(TMDBEpisodeDTO episode) {
        LocalDate now = LocalDate.now();
        return now.until(episode.getAirDate(), ChronoUnit.DAYS) < ALERT_THRESHOLD;
    }

    public ResponseEntity getUpcomingEpisodes() {
        Integer[] showIds = showsService.findAll().stream().map(ShowEntity::getTMDBId).toArray(Integer[]::new);

        try {
            TMDBEpisodeDTO[] episodes = this.alertService.findNextEpisodesByShowIds(showIds);
            TMDBEpisodeDTO[] upcomingEpisodes = Arrays.stream(episodes)
                    .filter(this::isEpisodeSoon)
                    .toArray(TMDBEpisodeDTO[]::new);
            return ResponseEntity.status(HttpStatus.OK).body(upcomingEpisodes);
        } catch (HttpStatusCodeException e) {
            // e has already been processed by our custom RestTemplateResponseErrorHandler so the error is right
            logger.error(e.toString());
            return ResponseEntity
                    .status(e.getRawStatusCode())
                    .headers(e.getResponseHeaders())
                    .body(e.getMessage());
        }
    }
}

