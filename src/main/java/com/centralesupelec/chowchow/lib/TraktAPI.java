package com.centralesupelec.chowchow.lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class TraktAPI {

    private RestTemplate restTemplate;
    private HttpEntity httpEntity;

    @Autowired
    public TraktAPI(RestTemplateBuilder restTemplateBuilder, @Value("${TRAKT_API_KEY:fakeDefaultAPIKey}") String traktAPIKey) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set("trakt-api-key", traktAPIKey);
        headers.set("trakt-api-version", "2");
        this.httpEntity = new HttpEntity(headers);
    }

    public <T> ResponseEntity<T> get(String url, Class<T> responseClass) throws HttpStatusCodeException {
        return restTemplate.exchange(
                url, HttpMethod.GET, this.httpEntity, responseClass);
    }
}
