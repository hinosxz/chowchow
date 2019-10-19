package com.centralesupelec.chowchow.likes.controllers;

import com.centralesupelec.chowchow.likes.domain.Mark;
import com.centralesupelec.chowchow.likes.domain.ShowRatingEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowRatingDTO {

  private final Long showId;
  private final Mark mark;

  @JsonCreator
  public ShowRatingDTO(@JsonProperty("show_id") Long show_id, @JsonProperty("mark") Mark mark) {
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
    return new ShowRatingDTO(showRatingEntity.getShowId(), showRatingEntity.getMark());
  }

  public static ShowRatingEntity toEntity(ShowRatingDTO showRatingDTO) {
    ShowRatingEntity showRating = new ShowRatingEntity();
    showRating.setUser(null);
    showRating.setShowId(showRatingDTO.getShowId());
    showRating.setMark(showRatingDTO.getMark());
    return showRating;
  }
}
