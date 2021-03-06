package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class AlertDTO {
  private final int ALERT_THRESHOLD = 14;

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

  public boolean isEpisodeSoon() {
    System.out.println(this.nextEpisodeToAir);
    LocalDate now = LocalDate.now();
    return !Objects.isNull(this.nextEpisodeToAir)
        && now.until(this.nextEpisodeToAir.getAirDate(), ChronoUnit.DAYS) < ALERT_THRESHOLD;
  }
}
