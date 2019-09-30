package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.show.service.ShowsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Controller
public class ShowsController {

    private ShowsServiceImpl showsServiceImpl;

    @Autowired
    public ShowsController(ShowsServiceImpl showsServiceImpl) {
        this.showsServiceImpl = showsServiceImpl;
    }

    @Async
    public CompletableFuture<Optional<ShowDTO>> getShowById(Long id) {
        return this.showsServiceImpl
                .getShowById(id)
                .thenApply(maybeShowEntity -> maybeShowEntity
                        .map(ShowDTO::fromEntity)
                );
    }
}
