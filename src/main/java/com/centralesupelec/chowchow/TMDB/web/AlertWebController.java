package com.centralesupelec.chowchow.TMDB.web;

import com.centralesupelec.chowchow.TMDB.controllers.AlertController;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping(path = "/alerts")
public class AlertWebController {

  private final Logger logger = LoggerFactory.getLogger(AlertController.class);

  private final AlertController alertController;

  @Autowired
  public AlertWebController(AlertController alertController) {
    this.alertController = alertController;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUpcomingEpisodes(HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    try {
      return ResponseEntity.status(HttpStatus.OK)
          .body(this.alertController.getUpcomingEpisodesForUser(userId));
    } catch (HttpStatusCodeException e) {
      // e has already been processed by our custom RestTemplateResponseErrorHandler so the error is
      // right
      logger.error(e.toString());
      return ResponseEntity.status(e.getRawStatusCode())
          .headers(e.getResponseHeaders())
          .body(e.getMessage());
    }
  }
}
