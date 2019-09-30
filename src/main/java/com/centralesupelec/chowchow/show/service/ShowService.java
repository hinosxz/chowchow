package com.centralesupelec.chowchow.show.service;

import com.centralesupelec.chowchow.show.domain.ShowEntity;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface ShowService {

    public CompletableFuture<Optional<ShowEntity>> getShowById(Long id);
}
