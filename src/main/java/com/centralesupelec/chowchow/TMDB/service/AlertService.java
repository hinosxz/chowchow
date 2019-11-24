package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;

/** Interface contracting the service retrieving alerts. */
public interface AlertService {

  /**
   * Retrieve the alert for a show with a given id.
   *
   * <p>Asynchronous method.
   *
   * @param showId the given show id
   * @return a CompletableFuture for the AlertDTO that represent the JSON object
   */
  @Async
  CompletableFuture<AlertDTO> findAlertByShowId(Integer showId);

  /**
   * Returns the alerts for the shows with a given list of ids.
   *
   * <p>Synchronous method that should use findAlertByShowId above.
   *
   * @param showIds the given list of show ids
   * @return a list of AlertDTO that represent the JSON object
   */
  List<AlertDTO> findAlertsByShowIds(List<Integer> showIds);
}
