package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import com.centralesupelec.chowchow.show.domain.ShowEntity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

// TODO add other premium fields

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class ShowDTO {
  private final Long id;
  private final int tmdbId;
  private final String name;
  private final int year;
  private final String overview;

  @JsonCreator
  ShowDTO(
      @JsonProperty("id") Long id,
      @JsonProperty("tmdb_id") Integer tmdbId,
      @JsonProperty("name") String name,
      @JsonProperty("year") Integer year,
      @JsonProperty("overview") String overview) {
    this.id = id;
    this.tmdbId = tmdbId;
    this.name = name;
    this.year = year;
    this.overview = overview;
  }

  public static ShowDTO fromEntity(ShowEntity showEntity) {
    return new ShowDTO(
        showEntity.getId(),
        showEntity.getTMDBId(),
        showEntity.getName(),
        showEntity.getYear(),
        null);
  }

  public static ShowEntity toEntity(ShowDTO showDTO) {
    ShowEntity showEntity = new ShowEntity();
    showEntity.setTMDBId(showDTO.tmdbId);
    showEntity.setName(showDTO.name);
    showEntity.setYear(showDTO.year);
    return showEntity;
  }

  public static ShowDTO fromTMDBShowDTO(TMDBShowDTO show) {
    return new ShowDTO(
        null, show.getId(), show.getName(), show.getFirstAirDate().getYear(), show.getOverview());
  }

  public Long getId() {
    return id;
  }

  public int getTMDBId() {
    return tmdbId;
  }

  public String getName() {
    return name;
  }

  public int getYear() {
    return year;
  }

  public String getOverview() {
    return overview;
  }
}
