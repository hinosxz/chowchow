package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UtilsDTO {

  @JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
  public static class CreatedByDTO {
    private final int id;
    private final String name;
    private final int gender;
    private final String profilePath;

    @JsonCreator
    public CreatedByDTO(
        @JsonProperty("id") int id,
        @JsonProperty("name") String name,
        @JsonProperty("gender") int gender,
        @JsonProperty("profile_path") String profilePath) {
      this.id = id;
      this.name = name;
      this.gender = gender;
      this.profilePath = "https://image.tmdb.org/t/p/original" + profilePath;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }

    public int getGender() {
      return gender;
    }

    public String getProfilePath() {
      return profilePath;
    }
  }

  @JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
  public static class GenreDTO {
    private final int id;
    private final String name;

    @JsonCreator
    public GenreDTO(@JsonProperty("id") int id, @JsonProperty("name") String name) {
      this.id = id;
      this.name = name;
    }

    public int getId() {
      return id;
    }

    public String getName() {
      return name;
    }
  }

  @JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
  public static class NetworkDTO {
    private final int id;
    private final String logoPath;
    private final String name;
    private final String originCountry;

    @JsonCreator
    public NetworkDTO(
        @JsonProperty("id") int id,
        @JsonProperty("logo_path") String logoPath,
        @JsonProperty("name") String name,
        @JsonProperty("origin_country") String originCountry) {
      this.id = id;
      this.logoPath = "https://image.tmdb.org/t/p/original" + logoPath;
      this.name = name;
      this.originCountry = originCountry;
    }

    public int getId() {
      return id;
    }

    public String getLogoPath() {
      return logoPath;
    }

    public String getName() {
      return name;
    }

    public String getOriginCountry() {
      return originCountry;
    }
  }

  @JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
  public static class ProductionCompanyDTO {
    private final int id;
    private final String logoPath;
    private final String name;
    private final String originCountry;

    @JsonCreator
    public ProductionCompanyDTO(
        @JsonProperty("id") int id,
        @JsonProperty("logo_path") String logoPath,
        @JsonProperty("name") String name,
        @JsonProperty("origin_country") String originCountry) {
      this.id = id;
      this.logoPath = logoPath != null ? "https://image.tmdb.org/t/p/original" + logoPath : null;
      this.name = name;
      this.originCountry = originCountry;
    }

    public int getId() {
      return id;
    }

    public String getLogoPath() {
      return logoPath;
    }

    public String getName() {
      return name;
    }

    public String getOriginCountry() {
      return originCountry;
    }
  }
}
