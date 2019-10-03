package com.centralesupelec.chowchow.search.web;

import com.centralesupelec.chowchow.search.controllers.SearchController;
import com.centralesupelec.chowchow.search.controllers.TraktSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public TraktSearch[] findShowsByName(@RequestParam(value = "name") String name) {
        return this.searchController.findShowsByName(name);
    }
}