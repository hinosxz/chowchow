package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.show.service.ShowsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Controller
public class ShowsController {

    private ShowsService showsService;

    @Autowired
    public ShowsController(ShowsService showsServiceImpl) {
        this.showsService = showsServiceImpl;
    }

    @Async
    public CompletableFuture<Optional<ShowDTO>> getShowById(Long id) {
        return this.showsService
                .getShowById(id)
                .thenApply(maybeShowEntity -> maybeShowEntity
                        .map(ShowDTO::fromEntity)
                );
    }
}
