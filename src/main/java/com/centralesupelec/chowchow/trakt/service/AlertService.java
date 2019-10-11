package com.centralesupelec.chowchow.trakt.service;
import com.centralesupelec.chowchow.lib.TraktAPI;
import com.centralesupelec.chowchow.trakt.controllers.TraktEpisode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;


@Service
@Transactional
public class AlertService {

    private TraktAPI<TraktEpisode> traktAPI;

    @Autowired
    public AlertService(TraktAPI<TraktEpisode> traktAPI) {
        this.traktAPI = traktAPI;
    }

    @Async
    public CompletableFuture<TraktEpisode> findNextEpisodeByShowId(Integer showId){
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/shows/" + showId + "/next_episode")
                .queryParam("extended", "full")
                .toUriString();

        return CompletableFuture.completedFuture(traktAPI.get(url, TraktEpisode.class).getBody());
    }

    public TraktEpisode[] findNextEpisodesByShowIds(Integer[] showIds) {
        CompletableFuture<TraktEpisode>[] promises = new CompletableFuture[showIds.length];
        for (int i = 0; i < showIds.length; i++) {
            promises[i] = findNextEpisodeByShowId(showIds[i]);
        }
        CompletableFuture.allOf(promises).join();
        return Arrays.stream(promises)
                .map(CompletableFuture::join)
                .filter(episode -> !Objects.isNull(episode))
                .toArray(TraktEpisode[]::new);
    }
}
