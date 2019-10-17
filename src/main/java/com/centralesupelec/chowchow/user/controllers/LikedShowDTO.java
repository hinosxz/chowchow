package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import com.centralesupelec.chowchow.likes.domain.Mark;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LikedShowDTO {
  private final Mark mark;
  private final TMDBShowDTO tmdbShowDTO;

  @JsonCreator
  public LikedShowDTO(
      @JsonProperty("mark") Mark mark, @JsonProperty("show") TMDBShowDTO tmdbShowDTO) {
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
