package com.centralesupelec.chowchow.trakt.service;
import com.centralesupelec.chowchow.lib.TraktAPI;

import com.centralesupelec.chowchow.trakt.controllers.TraktSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponentsBuilder;


@Service
@Transactional
public class SearchService {

    private TraktAPI<TraktSearchDTO[]> traktAPI;

    @Autowired
    public SearchService(TraktAPI traktAPI) {
        this.traktAPI = traktAPI;
    }

    public ResponseEntity findShowsByName(String name){
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/search/show")
                .queryParam("query", name)
                .toUriString();

        return traktAPI.get(url, TraktSearchDTO[].class);
    }
}
