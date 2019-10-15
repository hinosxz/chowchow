package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.TMDBEpisodeDTO;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Service
@Transactional
public class AlertService {

  private final SearchService searchService;

  @Autowired
  public AlertService(SearchService searchService) {
    this.searchService = searchService;
  }

  @Async
  public CompletableFuture<TMDBEpisodeDTO> findNextEpisodeByShowId(Long showId) {
    return Optional.ofNullable(this.searchService.findShowById(showId))
        .map(response -> response.getBody())
        .map(show -> show.getNextEpisodeToAir())
        .map(CompletableFuture::completedFuture)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
  }

  public List<TMDBEpisodeDTO> findNextEpisodesByShowIds(List<Long> showIds) {
    List<CompletableFuture<TMDBEpisodeDTO>> promises = new ArrayList<>();
    showIds.forEach(showId -> promises.add(findNextEpisodeByShowId(showId)));
    return promises.stream()
        .map(promise -> promise.join())
        .filter(episode -> !Objects.isNull(episode))
        .collect(Collectors.toList());
  }
}
