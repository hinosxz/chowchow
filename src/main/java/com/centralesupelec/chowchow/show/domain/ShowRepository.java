package com.centralesupelec.chowchow.show.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface ShowRepository  extends JpaRepository<ShowEntity,String> {

    @Async
    CompletableFuture<ShowEntity> findById(final Long id);
}
