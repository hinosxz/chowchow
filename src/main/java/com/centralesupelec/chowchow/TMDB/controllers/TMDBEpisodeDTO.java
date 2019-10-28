package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TMDBEpisodeDTO {
  private final LocalDate airDate;
  private final int episodeNumber;
  private final int id;
  private final String name;
  private final String overview;
  private final String productionCode;
  private final int seasonNumber;
  private final int showId;
  private final String stillPath;
  private final double voteAverage;
  private final int voteCount;

  @JsonCreator
  TMDBEpisodeDTO(
      @JsonProperty("air_date") String airDate,
      @JsonProperty("episode_number") int episodeNumber,
      @JsonProperty("id") int id,
      @JsonProperty("name") String name,
      @JsonProperty("overview") String overview,
      @JsonProperty("production_code") String productionCode,
      @JsonProperty("season_number") int seasonNumber,
      @JsonProperty("show_id") int showId,
      @JsonProperty("still_path") String stillPath,
      @JsonProperty("vote_average") double voteAverage,
      @JsonProperty("vote_count") int voteCount) {
    LocalDate parsedAirDate;
    try {
      parsedAirDate = LocalDate.parse(airDate, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (Exception e) {
      parsedAirDate = null;
    }
    this.airDate = parsedAirDate;

    this.episodeNumber = episodeNumber;
    this.id = id;
    this.name = name;
    this.overview = overview;
    this.productionCode = productionCode;
    this.seasonNumber = seasonNumber;
    this.showId = showId;
    this.stillPath = stillPath;
    this.voteAverage = voteAverage;
    this.voteCount = voteCount;
  }

  public LocalDate getAirDate() {
    return airDate;
  }

  public int getEpisodeNumber() {
    return episodeNumber;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getOverview() {
    return overview;
  }

  public String getProductionCode() {
    return productionCode;
  }

  public int getSeasonNumber() {
    return seasonNumber;
  }

  public int getShowId() {
    return showId;
  }

  public String getStillPath() {
    return stillPath;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public int getVoteCount() {
    return voteCount;
  }

  @Override
  public String toString() {
    return getName();
  }
}
