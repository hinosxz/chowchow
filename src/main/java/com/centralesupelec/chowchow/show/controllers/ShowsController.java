package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.search.service.SearchService;
import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.show.service.ShowsService;
import com.centralesupelec.chowchow.user.controllers.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Objects;
import java.util.Optional;

@Controller
public class ShowsController {

    private ShowsService showsService;
    private SearchService searchService

    @Autowired
    public ShowsController(
            ShowsService showsService,
            SearchService searchService
    ) {
        this.showsService = showsService;
        this.searchService = searchService;
    }

    public Optional<ShowDTO> getShowById(Long id, UserDTO userDTO) {
        return this.showsService
                .getShowById(id)
                .map(ShowDTO::fromEntity)
                .map(showDTO ->
                        Objects.isNull(userDTO.getSubscriptionType()) ?
                                showDTO : this.searchService.findShowById(showDTO.getTraktId())
                );
    }
}
