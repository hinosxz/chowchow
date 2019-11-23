package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.AlertDTO;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Transactional service implementing AlertService contract interface. It uses TMDB API to retrieve
 * the alerts.
 *
 * @see AlertService
 */
@Service
@Transactional
public class AlertServiceImpl implements AlertService {

  private final SearchServiceImpl searchServiceImpl;

  @Autowired
  public AlertServiceImpl(SearchServiceImpl searchServiceImpl) {
    this.searchServiceImpl = searchServiceImpl;
  }

  /**
   * Retrieve the alert through TMDB API for a show with a given id.
   *
   * @see AlertService#findAlertByShowId(Integer)
   */
  @Async
  public CompletableFuture<AlertDTO> findAlertByShowId(Integer showId) {
    return this.searchServiceImpl.findAlertByShowId(showId);
  }

  /**
   * Returns the alerts for the shows with a given list of ids using TMDB API.
   *
   * @see AlertService#findAlertsByShowIds(List)
   */
  public List<AlertDTO> findAlertsByShowIds(List<Integer> showIds) {
    List<CompletableFuture<AlertDTO>> promises =
        showIds.stream().map(this::findAlertByShowId).collect(Collectors.toList());
    return promises.stream()
        .map(CompletableFuture::join)
        .filter(episode -> !Objects.isNull(episode))
        .collect(Collectors.toList());
  }
}
