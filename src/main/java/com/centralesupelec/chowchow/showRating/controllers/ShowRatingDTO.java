package com.centralesupelec.chowchow.showRating.controllers;

import com.centralesupelec.chowchow.showRating.domain.Mark;
import com.centralesupelec.chowchow.showRating.domain.ShowRatingEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowRatingDTO {

  private final Long showId;
  private final Mark mark;

  @JsonCreator
  ShowRatingDTO(@JsonProperty("show_id") Long show_id, @JsonProperty("mark") Mark mark) {
    this.showId = show_id;
    this.mark = mark;
  }

  public Mark getMark() {
    return mark;
  }

  public Long getShowId() {
    return showId;
  }

  public static ShowRatingDTO fromEntity(ShowRatingEntity showRatingEntity) {
    return new ShowRatingDTO(showRatingEntity.getShow().getId(), showRatingEntity.getMark());
  }
}
