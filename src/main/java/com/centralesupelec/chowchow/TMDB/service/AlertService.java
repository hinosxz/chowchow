package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.TMDBEpisodeDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlertService {

  private final SearchService searchService;

  @Autowired
  public AlertService(SearchService searchService) {
    this.searchService = searchService;
  }

  @Async
  public CompletableFuture<TMDBEpisodeDTO> findNextEpisodeByShowId(Integer showId) {
    return this.searchService.findShowById(showId).thenApply(TMDBShowDTO::getNextEpisodeToAir);
  }

  public List<TMDBEpisodeDTO> findNextEpisodesByShowIds(List<Integer> showIds) {
    List<CompletableFuture<TMDBEpisodeDTO>> promises =
        showIds.stream().map(this::findNextEpisodeByShowId).collect(Collectors.toList());
    return promises.stream()
        .map(CompletableFuture::join)
        .filter(episode -> !Objects.isNull(episode))
        .collect(Collectors.toList());
  }
}
