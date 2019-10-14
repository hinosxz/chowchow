package com.centralesupelec.chowchow.show.service;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.domain.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class ShowsService {
    private final ShowRepository showRepository;

    @Autowired
    public ShowsService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Async
    public CompletableFuture<Optional<ShowEntity>> getShowById(Long id){
        return this.showRepository.findById(id).thenApply(Optional::ofNullable);
    }

    public ShowEntity saveShow(ShowEntity showEntity) {
        return this.showRepository.save(showEntity);
    }
    public List<ShowEntity> findAll(){
        return this.showRepository
                .findAll();
    }
}
