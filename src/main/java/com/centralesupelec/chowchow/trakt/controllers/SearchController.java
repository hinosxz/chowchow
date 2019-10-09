package com.centralesupelec.chowchow.trakt.controllers;

import com.centralesupelec.chowchow.trakt.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    public ResponseEntity findShowsByName(String name) {
        return this.searchService
                .findShowsByName(name);
    }
}

