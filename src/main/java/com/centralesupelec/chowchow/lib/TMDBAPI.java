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

/** Fetcher service used to call the TMDB API */
@Service
public class TMDBAPI {

  private String TMDBAPIKey;
  private RestTemplate restTemplate;
  private HttpEntity httpEntity;

  /**
   * Uses the TMDB_API_KEY env variable to set the api_key query parameter
   *
   * @param restTemplateBuilder
   * @param TMDBAPIKey
   */
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

  /**
   * Performs GET requests on the TMDB API
   *
   * @return ResponseEntity
   * @throws HttpStatusCodeException
   */
  public <T> ResponseEntity<T> get(UriComponentsBuilder urlBuilder, Class<T> responseClass)
      throws HttpStatusCodeException {
    String url = urlBuilder.queryParam("api_key", this.TMDBAPIKey).build(false).toUriString();
    return this.restTemplate.exchange(url, HttpMethod.GET, this.httpEntity, responseClass);
  }
}
