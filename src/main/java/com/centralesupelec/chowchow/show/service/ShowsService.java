package com.centralesupelec.chowchow.show.service;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.domain.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShowsService {
    private final ShowRepository showRepository;

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
    public List<ShowEntity> findAll(){
        return this.showRepository
                .findAll();
    }
}
