package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class TMDBSeasonDTO {
  private final LocalDate airDate;
  private final List<TMDBEpisodeDTO> episodes;
  private final int episodeCount;
  private final int id;
  private final String name;
  private final String overview;
  private final String posterPath;
  private final int seasonNumber;

  @JsonCreator
  public TMDBSeasonDTO(
      @JsonProperty("air_date") String airDate,
      @JsonProperty("episodes") List<TMDBEpisodeDTO> episodes,
      @JsonProperty("episode_count") int episodeCount,
      @JsonProperty("id") int id,
      @JsonProperty("name") String name,
      @JsonProperty("overview") String overview,
      @JsonProperty("poster_path") String posterPath,
      @JsonProperty("season_number") int seasonNumber) {
    LocalDate parsedAirDate;
    try {
      parsedAirDate = LocalDate.parse(airDate, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (Exception e) {
      parsedAirDate = null;
    }
    this.airDate = parsedAirDate;
    this.episodes = episodes;
    this.episodeCount = episodeCount;
    this.id = id;
    this.name = name;
    this.overview = overview;
    this.posterPath = "https://image.tmdb.org/t/p/original" + posterPath;
    this.seasonNumber = seasonNumber;
  }

  public LocalDate getAirDate() {
    return airDate;
  }

  public List<TMDBEpisodeDTO> getEpisodes() {
    return episodes;
  }

  public int getEpisodeCount() {
    return episodeCount;
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

  public String getPosterPath() {
    return posterPath;
  }

  public int getSeasonNumber() {
    return seasonNumber;
  }
}
