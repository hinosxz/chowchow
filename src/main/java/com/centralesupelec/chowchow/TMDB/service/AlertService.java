package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.TMDBEpisodeDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

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
        TMDBShowDTO show = this.searchService.findShowById(showId).getBody();
        TMDBEpisodeDTO episode = show.getNextEpisodeToAir();
        return CompletableFuture.completedFuture(episode);
    }

    public TMDBEpisodeDTO[] findNextEpisodesByShowIds(Integer[] showIds) {
        CompletableFuture<TMDBEpisodeDTO>[] promises = new CompletableFuture[showIds.length];
        for (int i = 0; i < showIds.length; i++) {
            promises[i] = this.findNextEpisodeByShowId(showIds[i]);
        }
        CompletableFuture.allOf(promises).join();
        return Arrays.stream(promises)
                .map(CompletableFuture::join)
                .filter(episode -> !Objects.isNull(episode))
                .toArray(TMDBEpisodeDTO[]::new);
    }
}
