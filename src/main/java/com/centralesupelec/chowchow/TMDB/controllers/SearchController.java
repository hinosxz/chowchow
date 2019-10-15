package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class SearchController {

  private final SearchService searchService;

  @Autowired
  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  public ResponseEntity<TMDBSearchDTO> findShowsByName(String name) throws HttpStatusCodeException {
    return this.searchService.findShowsByName(name);
  }

  public ResponseEntity<TMDBShowDTO> findShowById(Long id) throws HttpStatusCodeException {
    return this.searchService.findShowById(id);
  }
}
