package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBSearchDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import com.centralesupelec.chowchow.lib.TMDBAPI;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional
public class SearchService {

  private final TMDBAPI TMDBAPI;

  @Autowired
  public SearchService(TMDBAPI TMDBAPI) {
    this.TMDBAPI = TMDBAPI;
  }

  public TMDBSearchDTO findShowsByName(String name) throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/tv")
            .queryParam("query", name);
    return TMDBAPI.get(urlBuilder, TMDBSearchDTO.class).getBody();
  }

  @Async
  public CompletableFuture<TMDBShowDTO> findShowById(Integer id) throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/tv")
            .path(String.format("/%d", id));
    return CompletableFuture.completedFuture(TMDBAPI.get(urlBuilder, TMDBShowDTO.class).getBody());
  }

  @Async
  // Call the same endpoint but return only the show name and id and the next episode
  public CompletableFuture<AlertDTO> findAlertByShowId(Integer showId)
      throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/tv")
            .path(String.format("/%d", showId));
    return CompletableFuture.completedFuture(TMDBAPI.get(urlBuilder, AlertDTO.class).getBody());
  }
}
