package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class TMDBSearchDTO {
  private final int page;
  private final int totalResults;
  private final int totalPages;
  private final TMDBSearchShowDTO[] results;

  @JsonCreator
  TMDBSearchDTO(
      @JsonProperty("page") int page,
      @JsonProperty("total_results") int totalResults,
      @JsonProperty("total_pages") int totalPages,
      @JsonProperty("results") TMDBSearchShowDTO[] results) {
    this.page = page;
    this.totalResults = totalResults;
    this.totalPages = totalPages;
    this.results = results;
  }

  public int getPage() {
    return page;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public TMDBSearchShowDTO[] getResults() {
    return results;
  }

  @Override
  public String toString() {
    return Arrays.toString(getResults());
  }
}
