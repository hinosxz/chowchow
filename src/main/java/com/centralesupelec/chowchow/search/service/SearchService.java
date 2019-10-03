package com.centralesupelec.chowchow.search.service;

import com.centralesupelec.chowchow.lib.RestTemplateResponseErrorHandler;
import com.centralesupelec.chowchow.search.controllers.TraktSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import org.springframework.web.util.UriComponentsBuilder;


@Service
@Transactional
public class SearchService {

    Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private RestTemplateBuilder builder;

    @Value("${TRAKT_API_KEY:fakeDefaultAPIKey}")
    private String traktAPIKey;

    public ResponseEntity findShowsByName(String name){
        RestTemplate restTemplate = this.builder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("trakt-api-key", traktAPIKey);
        headers.set("trakt-api-version", "2");
        HttpEntity entity = new HttpEntity(headers);

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/search/show")
                .queryParam("query", name)
                .toUriString();

        try {
            ResponseEntity<TraktSearch[]> response = restTemplate.exchange(
                    url, HttpMethod.GET, entity, TraktSearch[].class);
            return response;
        } catch (HttpStatusCodeException e) {
            // e has already been processed by our custom RestTemplateResponseErrorHandler so the error is right
            logger.error(e.toString());
            return ResponseEntity
                    .status(e.getRawStatusCode())
                    .headers(e.getResponseHeaders())
                    .body(e.getMessage());
        }
    }
}
