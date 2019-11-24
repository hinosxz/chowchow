package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBSearchDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBSeasonDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import java.util.concurrent.CompletableFuture;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.HttpStatusCodeException;

/** Interface contracting the service performing searches. */
public interface SearchService {

  /**
   * Search the shows matching a given name.
   *
   * @param name the given name
   * @return a TMDBSearchDTO object representing the JSON response.
   * @throws HttpStatusCodeException
   */
  TMDBSearchDTO findShowsByName(String name) throws HttpStatusCodeException;

  /**
   * Fetches the season with given number from a show with a given id.
   *
   * @param showId the given show id
   * @param seasonNumber the given season number
   * @return a TMDBSeasonDTO object representing the JSON response.
   * @throws HttpStatusCodeException
   */
  TMDBSeasonDTO findShowSeasonById(Integer showId, Integer seasonNumber)
      throws HttpStatusCodeException;

  /**
   * Fetches a show with a given id.
   *
   * @param showId the given showId
   * @return a CompletableFuture for the TMDBShowDTO that represent the JSON object
   * @throws HttpStatusCodeException
   */
  @Async
  CompletableFuture<TMDBShowDTO> findShowById(Integer showId) throws HttpStatusCodeException;

  /**
   * Retrieve the alert for a show with a given id.
   *
   * <p>Asynchronous method.
   *
   * @param showId the given show id
   * @return a CompletableFuture for the AlertDTO that represent the JSON object
   * @throws HttpStatusCodeException
   */
  @Async
  CompletableFuture<AlertDTO> findAlertByShowId(Integer showId) throws HttpStatusCodeException;
}
