package com.centralesupelec.chowchow.trakt.service;

import com.centralesupelec.chowchow.lib.TraktAPI;
import com.centralesupelec.chowchow.trakt.controllers.TraktSearchDTO;
import com.centralesupelec.chowchow.trakt.controllers.TraktShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;


@Service
@Transactional
public class SearchService {

    private final TraktAPI traktAPI;

    @Autowired
    public SearchService(TraktAPI traktAPI) {
       this.traktAPI = traktAPI;
    }

    public ResponseEntity<TraktSearchDTO[]> findShowsByName(String name) throws HttpStatusCodeException {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/search/show")
                .queryParam("query", name)
                .toUriString();
        return traktAPI.get(url, TraktSearchDTO[].class);
    }

    public ResponseEntity<TraktShowDTO> findShowByTraktId(int traktId) throws HttpStatusCodeException {
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/shows")
                .path(String.format("/%d", traktId))
                .queryParam("extended", "full")
                .toUriString();
        return traktAPI.get(url, TraktShowDTO.class);
    }
}
