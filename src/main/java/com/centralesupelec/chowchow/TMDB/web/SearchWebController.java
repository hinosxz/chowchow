package com.centralesupelec.chowchow.TMDB.web;

import com.centralesupelec.chowchow.TMDB.controllers.SearchController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/search")
public class SearchWebController {

    private final SearchController searchController;

    @Autowired
    public SearchWebController(SearchController searchController) {
        this.searchController = searchController;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity findShowsByName(@RequestParam(value = "name") String name) {
        return this.searchController.findShowsByName(name);
    }
}