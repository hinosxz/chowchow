package com.centralesupelec.chowchow.show.service;

import com.centralesupelec.chowchow.show.controllers.ShowDTO;
import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.domain.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ShowsService {
    private ShowRepository showRepository;

    @Autowired
    public ShowsService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Optional<ShowEntity> getShowById(Long id){
        return Optional.ofNullable(this.showRepository.findById(id));
    }

    public ShowEntity saveShow(ShowEntity showEntity) {
        return this.showRepository.save(showEntity);
    }
}
