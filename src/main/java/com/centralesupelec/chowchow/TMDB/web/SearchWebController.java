package com.centralesupelec.chowchow.TMDB.web;

import com.centralesupelec.chowchow.TMDB.controllers.SearchController;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBSearchDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping(path = "/search")
public class SearchWebController {

    private final Logger logger = LoggerFactory.getLogger(SearchController.class);

    private final SearchController searchController;

    @Autowired
    public SearchWebController(SearchController searchController) {
        this.searchController = searchController;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findShowsByName(@RequestParam(value = "name") String name) {
        try {
            TMDBSearchDTO search = this.searchController.findShowsByName(name).getBody();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(search);
        } catch (HttpStatusCodeException e) {
            // e has already been processed by our custom RestTemplateResponseErrorHandler so the error is right
            logger.error(e.toString());
            return ResponseEntity
                    .status(e.getRawStatusCode())
                    .body(e.getMessage());
        }
    }
}
