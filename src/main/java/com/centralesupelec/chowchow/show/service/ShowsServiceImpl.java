package com.centralesupelec.chowchow.show.service;

import com.centralesupelec.chowchow.show.controllers.ShowDTO;
import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.domain.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Service
@Transactional
public class ShowsServiceImpl implements ShowService {
    private ShowRepository showRepository;

    @Autowired
    public ShowsServiceImpl(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public CompletableFuture<Optional<ShowEntity>> getShowById(Long id){
        return this.showRepository
                .findById(id)
                .thenApply(Optional::ofNullable);
    }

    private static Function<Optional<ShowEntity>, Optional<ShowDTO>> mapMaybeShowEntityToShowDTO = maybeShowEntity -> maybeShowEntity
            .<ShowDTO>map(ShowDTO::fromEntity);
}
