package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;

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
    return this.page;
  }

  public int getTotalResults() {
    return this.totalResults;
  }

  public int getTotalPages() {
    return this.totalPages;
  }

  public TMDBSearchShowDTO[] getResults() {
    return this.results;
  }

  @Override
  public String toString() {
    return Arrays.toString(this.getResults());
  }
}
