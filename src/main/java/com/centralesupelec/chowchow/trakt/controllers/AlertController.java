package com.centralesupelec.chowchow.trakt.controllers;

import com.centralesupelec.chowchow.trakt.service.AlertService;
import com.centralesupelec.chowchow.trakt.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Controller
public class AlertController {

    private Logger logger = LoggerFactory.getLogger(AlertController.class);

    private final int ALERT_THRESHOLD = 3;

    private AlertService alertService;

    @Autowired
    public AlertController(AlertService alertService) {
        this.alertService = alertService;
    }

    private boolean isEpisodeSoon(TraktEpisode episode) {
        Instant now = Instant.now();
        return now.until(episode.getFirstAired(), ChronoUnit.DAYS) < ALERT_THRESHOLD;
    }

    public ResponseEntity getUpcomingEpisodes() {
        // TODO replace with actual request to DB for showIds
        int[] showIds = { 99046, 60158, 93720, 60300 };
        try {
            TraktEpisode[] episodes = this.alertService.findNextEpisodesByShowIds(showIds);
            TraktEpisode[] upcomingEpisodes = Arrays.stream(episodes)
                    .filter(this::isEpisodeSoon)
                    .toArray(TraktEpisode[]::new);
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

