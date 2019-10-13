package com.centralesupelec.chowchow.TMDB.web;

import com.centralesupelec.chowchow.TMDB.controllers.AlertController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/alerts")
public class AlertWebController {

    private final AlertController alertController;

    @Autowired
    public AlertWebController(AlertController alertController) {
        this.alertController = alertController;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUpcomingEpisodes() {
        return this.alertController.getUpcomingEpisodes();
    }
}
