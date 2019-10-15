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
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class TMDBAPI {

  private String TMDBAPIKey;
  private RestTemplate restTemplate;
  private HttpEntity httpEntity;

  @Autowired
  public TMDBAPI(
      RestTemplateBuilder restTemplateBuilder,
      @Value("${TMDB_API_KEY:fakeDefaultAPIKey}") String TMDBAPIKey) {
    this.restTemplate =
        restTemplateBuilder.errorHandler(new RestTemplateResponseErrorHandler()).build();
    this.TMDBAPIKey = TMDBAPIKey;

    HttpHeaders headers = new HttpHeaders();
    this.httpEntity = new HttpEntity(headers);
  }

  public <T> ResponseEntity<T> get(UriComponentsBuilder urlBuilder, Class<T> responseClass)
      throws HttpStatusCodeException {
    String url = urlBuilder.queryParam("api_key", this.TMDBAPIKey).toUriString();
    return this.restTemplate.exchange(url, HttpMethod.GET, this.httpEntity, responseClass);
  }
}
