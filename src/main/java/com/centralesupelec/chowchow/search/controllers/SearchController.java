package com.centralesupelec.chowchow.search.controllers;

import com.centralesupelec.chowchow.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    public TraktSearch[] findShowsByName(String name) {
        return this.searchService
                .findShowsByName(name);
    }
}

