package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.service.AlertService;
import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Controller
public class AlertController {

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
        return now.until(episode.getAirDate(), ChronoUnit.DAYS) < this.ALERT_THRESHOLD;
    }

    public TMDBEpisodeDTO[] getUpcomingEpisodes() throws HttpStatusCodeException {
        Integer[] showIds =
                this.showsService.findAll().stream().map(ShowEntity::getTMDBId).toArray(Integer[]::new);

        TMDBEpisodeDTO[] episodes = this.alertService.findNextEpisodesByShowIds(showIds);
        return Arrays.stream(episodes).filter(this::isEpisodeSoon).toArray(TMDBEpisodeDTO[]::new);
    }
}
