package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBSearchDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBSeasonDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import com.centralesupelec.chowchow.lib.TMDBAPI;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Transactional service implementing SearchService contract interface.
 *
 * <p>It uses TMDB API to perform search and fetch operations.
 *
 * @see SearchService
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {

  /** The TMDB API used to perform the calls */
  /** @see TMDBAPI */
  private final TMDBAPI TMDBAPI;

  @Autowired
  public SearchServiceImpl(TMDBAPI TMDBAPI) {
    this.TMDBAPI = TMDBAPI;
  }

  /**
   * Search the shows matching a given name.
   *
   * <p>It uses TMDB API to perform the search.
   *
   * @see SearchService#findShowsByName(String)
   */
  public TMDBSearchDTO findShowsByName(String name) throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/tv")
            .queryParam("query", name);
    return TMDBAPI.get(urlBuilder, TMDBSearchDTO.class).getBody();
  }

  /**
   * Fetches the season with given number from a show with a given id.
   *
   * <p>It uses TMDB API to fetch the season.
   *
   * @see SearchService#findShowSeasonById(Integer, Integer)
   */
  public TMDBSeasonDTO findShowSeasonById(Integer showId, Integer seasonNumber)
      throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/tv")
            .path(String.format("/%d", showId))
            .path("/season")
            .path(String.format("/%d", seasonNumber));
    return TMDBAPI.get(urlBuilder, TMDBSeasonDTO.class).getBody();
  }

  /**
   * Fetches a show with a given id.
   *
   * <p>It uses TMDB API to fetch the show.
   *
   * @see SearchService#findShowById(Integer)
   */
  @Async
  public CompletableFuture<TMDBShowDTO> findShowById(Integer showId)
      throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/tv")
            .path(String.format("/%d", showId));
    return CompletableFuture.completedFuture(TMDBAPI.get(urlBuilder, TMDBShowDTO.class).getBody());
  }

  /**
   * Retrieve the alert through TMDB API for a show with a given id.
   *
   * <p>Calls the same endpoint than findShowById but return only the show name and id and the next
   * episode
   *
   * @see AlertService#findAlertByShowId(Integer)
   */
  @Async
  public CompletableFuture<AlertDTO> findAlertByShowId(Integer showId)
      throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/tv")
            .path(String.format("/%d", showId));
    return CompletableFuture.completedFuture(TMDBAPI.get(urlBuilder, AlertDTO.class).getBody());
  }
}
