package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.show.service.ShowsService;
import com.centralesupelec.chowchow.trakt.controllers.SearchController;
import com.centralesupelec.chowchow.trakt.service.SearchService;
import com.centralesupelec.chowchow.user.controllers.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.Optional;

@Controller
public class ShowsController {

    private final Logger logger = LoggerFactory.getLogger(ShowsController.class);

    private final ShowsService showsService;
    private final SearchService searchService;

    @Autowired
    public ShowsController(
            ShowsService showsService,
            SearchService searchService
    ) {
        this.showsService = showsService;
        this.searchService = searchService;
    }

    public Optional<ShowDTO> getShowById(Long id, UserDTO userDTO) {
        Optional<ShowDTO> maybeShowDTO = this.showsService
                .getShowById(id)
                .map(ShowDTO::fromEntity);
        if(maybeShowDTO.isPresent() && userDTO.isPremium()) {
            try {
                maybeShowDTO = Optional.ofNullable(this.searchService
                        .findShowByTraktId(maybeShowDTO.get().getTraktId()
                        )
                        .getBody())
                        .map(ShowDTO::fromTraktShowDTO);
            } catch (HttpStatusCodeException e) {
                logger.error(e.toString());
            }
        }
        return maybeShowDTO;
    }
}
