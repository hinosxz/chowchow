package com.centralesupelec.chowchow.likes.controllers;

import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import com.centralesupelec.chowchow.likes.domain.Mark;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LikeDTO {

  private final Mark mark;
  private final TMDBShowDTO tmdbShowDTO;

  @JsonCreator
  public LikeDTO(@JsonProperty("mark") Mark mark, @JsonProperty("show") TMDBShowDTO tmdbShowDTO) {
    this.mark = mark;
    this.tmdbShowDTO = tmdbShowDTO;
  }

  public Mark getMark() {
    return mark;
  }

  public TMDBShowDTO getTmdbShowDTO() {
    return tmdbShowDTO;
  }
}
