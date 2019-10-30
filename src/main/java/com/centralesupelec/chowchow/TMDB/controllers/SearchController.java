package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class SearchController {

  private final SearchService searchService;

  @Autowired
  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  public TMDBSearchDTO findShowsByName(String name) throws HttpStatusCodeException {
    return this.searchService.findShowsByName(name);
  }

  public TMDBShowDTO findShowById(Integer id) throws HttpStatusCodeException {
    return this.searchService.findShowById(id).join();
  }
}
