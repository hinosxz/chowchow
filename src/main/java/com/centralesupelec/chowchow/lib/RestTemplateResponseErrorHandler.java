package com.centralesupelec.chowchow.lib;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

  private final Logger LOGGER = LoggerFactory.getLogger(TMDBAPI.class);

  @Override
  public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
    return (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
        || httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
  }

  @Override
  public void handleError(ClientHttpResponse httpResponse) throws IOException {
    // If the target API cannot answer, we send a 502
    if (httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
        || httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
      HttpServerErrorException e = new HttpServerErrorException(HttpStatus.BAD_GATEWAY);
      LOGGER.error(e.toString());
      throw e;
    }
    // If it returns a client error, we send a 400
    else if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
      HttpClientErrorException e = new HttpClientErrorException(HttpStatus.BAD_GATEWAY);
      LOGGER.error(e.toString());
      throw e;
    }
  }
}
