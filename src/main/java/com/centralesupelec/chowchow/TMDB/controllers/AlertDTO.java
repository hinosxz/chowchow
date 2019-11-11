package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AlertDTO {
  private final Integer showId;
  private final String showName;
  private final TMDBEpisodeDTO nextEpisodeToAir;

  @JsonCreator
  AlertDTO(
      @JsonProperty("show_id") @JsonAlias({"id"}) Integer showId,
      @JsonProperty("show_name") @JsonAlias({"name"}) String showName,
      @JsonProperty("episode") @JsonAlias({"next_episode_to_air"})
          TMDBEpisodeDTO nextEpisodeToAir) {

    this.showId = showId;
    this.showName = showName;
    this.nextEpisodeToAir = nextEpisodeToAir;
  }

  public Integer getShowId() {
    return showId;
  }

  public String getShowName() {
    return showName;
  }

  public TMDBEpisodeDTO getNextEpisodeToAir() {
    return nextEpisodeToAir;
  }

  @Override
  public String toString() {
    return getShowName() + ": " + getNextEpisodeToAir().toString();
  }
}
