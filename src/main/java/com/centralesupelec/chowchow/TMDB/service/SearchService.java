package com.centralesupelec.chowchow.TMDB.service;

import com.centralesupelec.chowchow.TMDB.controllers.TMDBSearchDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import com.centralesupelec.chowchow.lib.TMDBAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Transactional
public class SearchService {

  private final TMDBAPI TMDBAPI;

  @Autowired
  public SearchService(TMDBAPI TMDBAPI) {
    this.TMDBAPI = TMDBAPI;
  }

  public ResponseEntity<TMDBSearchDTO> findShowsByName(String name) throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/tv")
            .queryParam("query", name);
    return TMDBAPI.get(urlBuilder, TMDBSearchDTO.class);
  }

  public ResponseEntity<TMDBShowDTO> findShowById(Long id) throws HttpStatusCodeException {
    UriComponentsBuilder urlBuilder =
        UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/tv")
            .path(String.format("/%d", id));
    return TMDBAPI.get(urlBuilder, TMDBShowDTO.class);
  }
}
