package com.centralesupelec.chowchow.search.service;

import com.centralesupelec.chowchow.lib.RestTemplateResponseErrorHandler;
import com.centralesupelec.chowchow.search.controllers.TraktSearchDTO;
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

import java.util.Optional;

@Service
@Transactional
public class SearchService {

    private Logger logger = LoggerFactory.getLogger(SearchService.class);

    private RestTemplate restTemplate;

    @Autowired
    public SearchService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    @Value("${TRAKT_API_KEY:fakeDefaultAPIKey}")
    private String traktAPIKey;

    public ResponseEntity findShowsByName(String name){
        HttpHeaders httpHeaders = this.getTraktHttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/search/show")
                .queryParam("query", name)
                .toUriString();
        return this.makeRequest(url, HttpMethod.GET, httpEntity, TraktSearchDTO[].class);
    }

    public Optional<TraktSearchDTO> findShowById(int id) {
        HttpHeaders httpHeaders = this.getTraktHttpHeaders();
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.trakt.tv/shows")
                .path(Integer.toString(id))
                .queryParam("extended", "full")
                .toUriString();
        ResponseEntity<TraktSearchDTO> responseEntity =  this.makeRequest(url, HttpMethod.GET, httpEntity, TraktSearchDTO.class);
        responseEntity.
    }

    private HttpHeaders getTraktHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("trakt-api-key", traktAPIKey);
        headers.set("trakt-api-version", "2");
        return  headers;
    }

    private ResponseEntity makeRequest(String url, HttpMethod httpMethod, HttpEntity httpEntity, Class returnedClass) {
        try {
            return this.restTemplate.exchange(
                    url, httpMethod, httpEntity, returnedClass);
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
