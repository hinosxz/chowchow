package com.centralesupelec.chowchow.TMDB.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpStatusCodeException;

@Controller
public class SearchController {

  private final SearchServiceImpl searchServiceImpl;

  @Autowired
  public SearchController(SearchServiceImpl searchServiceImpl) {
    this.searchServiceImpl = searchServiceImpl;
  }

  public TMDBSearchDTO findShowsByName(String name) throws HttpStatusCodeException {
    return this.searchServiceImpl.findShowsByName(name);
  }

  public TMDBSeasonDTO findShowSeasonById(Integer showId, Integer seasonNumber)
      throws HttpStatusCodeException {
    return this.searchServiceImpl.findShowSeasonById(showId, seasonNumber);
  }

  public TMDBShowDTO findShowById(Integer id) throws HttpStatusCodeException {
    return this.searchServiceImpl.findShowById(id).join();
  }
}
