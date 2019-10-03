package com.centralesupelec.chowchow.search.service;

import com.centralesupelec.chowchow.search.controllers.TraktSearch;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class SearchService {

    @Value("${TRAKT_API_KEY:fakeDefaultAPIKey}")
    private String traktAPIKey;

    public TraktSearch[] findShowsByName(String name){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("trakt-api-key", traktAPIKey);
        headers.set("trakt-api-version", "2");
        HttpEntity entity = new HttpEntity(headers);

        Map<String, String> params = new HashMap<>();
        params.put("query", name);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/search/show")
                .queryParam("query", name)
                .toUriString();

        ResponseEntity<TraktSearch[]> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, TraktSearch[].class);
        return response.getBody();
    }
}
