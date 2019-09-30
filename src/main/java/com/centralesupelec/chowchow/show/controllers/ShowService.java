package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.domain.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowService {
    private ShowRepository showRepository;

    @Autowired
    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public ShowDTO getShowById(Long id){
        ShowEntity showEntity = this.showRepository.findById(id);
        return ShowDTO.fromEntity(showEntity);
    }

}
