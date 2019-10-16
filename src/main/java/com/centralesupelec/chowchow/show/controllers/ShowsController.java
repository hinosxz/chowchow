package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
import com.centralesupelec.chowchow.show.service.ShowsService;
import com.centralesupelec.chowchow.user.controllers.UserDTO;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class ShowsController {

  private final Logger logger = LoggerFactory.getLogger(ShowsController.class);

  private final ShowsService showsService;
  private final SearchService searchService;

  @Autowired
  public ShowsController(ShowsService showsService, SearchService searchService) {
    this.showsService = showsService;
    this.searchService = searchService;
  }

  public Optional<ShowDTO> getShowById(Long id, UserDTO userDTO) {
    Optional<ShowDTO> maybeShowDTO =
        this.showsService
            .getShowById(id)
            .thenApply(
                maybeShowEntity ->
                    maybeShowEntity.map(showEntity -> ShowDTO.fromEntity(showEntity)))
            .join();
    if (maybeShowDTO.isPresent() && userDTO.isPremium()) {
      try {
        maybeShowDTO =
            Optional.ofNullable(
                    this.searchService.findShowById(maybeShowDTO.get().getTMDBId()).getBody())
                .map(ShowDTO::fromTMDBShowDTO);
      } catch (HttpStatusCodeException e) {
        logger.error(e.toString());
      }
    }
    return maybeShowDTO;
  }
}
