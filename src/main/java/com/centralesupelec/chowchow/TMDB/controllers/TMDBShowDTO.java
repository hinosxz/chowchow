package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

class CreatedByDTO {
  private final int id;
  private final String name;
  private final int gender;
  private final String profilePath;

  @JsonCreator
  CreatedByDTO(
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

  @Override
  public String toString() {
    return getName();
  }
}

class GenreDTO {
  private final int id;
  private final String name;

  @JsonCreator
  GenreDTO(@JsonProperty("id") int id, @JsonProperty("name") String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return getName();
  }
}

class NetworkDTO {
  private final int id;
  private final String logoPath;
  private final String name;
  private final String originCountry;

  @JsonCreator
  NetworkDTO(
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

  @Override
  public String toString() {
    return getName();
  }
}

class ProductionCompanyDTO {
  private final int id;
  private final String logoPath;
  private final String name;
  private final String originCountry;

  @JsonCreator
  ProductionCompanyDTO(
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

  @Override
  public String toString() {
    return getName();
  }
}

class SeasonDTO {
  private final LocalDate airDate;
  private final int episodeCount;
  private final int id;
  private final String name;
  private final String overview;
  private final String posterPath;
  private final int seasonNumber;

  @JsonCreator
  SeasonDTO(
      @JsonProperty("air_date") String airDate,
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

  @Override
  public String toString() {
    return getName();
  }
}

public class TMDBShowDTO {
  private final String backdropPath;
  private final List<CreatedByDTO> createdBy;
  private final List<Integer> episodeRunTime;
  private final LocalDate firstAirDate;
  private final List<GenreDTO> genres;
  private final String homepage;
  private final Long id;
  private final boolean inProduction;
  private final List<String> languages;
  private final LocalDate lastAirDate;
  private final TMDBEpisodeDTO lastEpisodeToAir;
  private final String name;
  private final TMDBEpisodeDTO nextEpisodeToAir;
  private final List<NetworkDTO> networks;
  private final int numberOfEpisodes;
  private final int numberOfSeasons;
  private final List<String> originCountry;
  private final String originalLanguage;
  private final String originalName;
  private final String overview;
  private final double popularity;
  private final String posterPath;
  private final List<SeasonDTO> seasons;
  private final String status;
  private final String type;
  private final double voteAverage;
  private final int voteCount;

  @JsonCreator
  TMDBShowDTO(
      @JsonProperty("backdrop_path") String backdropPath,
      @JsonProperty("created_by") List<CreatedByDTO> createdBy,
      @JsonProperty("episode_run_time") List<Integer> episodeRunTime,
      @JsonProperty("first_air_date") String firstAirDate,
      @JsonProperty("genres") List<GenreDTO> genres,
      @JsonProperty("homepage") String homepage,
      @JsonProperty("id") Long id,
      @JsonProperty("in_production") boolean inProduction,
      @JsonProperty("languages") List<String> languages,
      @JsonProperty("last_air_date") String lastAirDate,
      @JsonProperty("last_episode_to_air") TMDBEpisodeDTO lastEpisodeToAir,
      @JsonProperty("name") String name,
      @JsonProperty("next_episode_to_air") TMDBEpisodeDTO nextEpisodeToAir,
      @JsonProperty("networks") List<NetworkDTO> networks,
      @JsonProperty("number_of_episodes") int numberOfEpisodes,
      @JsonProperty("number_of_seasons") int numberOfSeasons,
      @JsonProperty("origin_country") List<String> originCountry,
      @JsonProperty("original_language") String originalLanguage,
      @JsonProperty("original_name") String originalName,
      @JsonProperty("overview") String overview,
      @JsonProperty("popularity") double popularity,
      @JsonProperty("poster_path") String posterPath,
      @JsonProperty("seasons") List<SeasonDTO> seasons,
      @JsonProperty("status") String status,
      @JsonProperty("type") String type,
      @JsonProperty("vote_average") double voteAverage,
      @JsonProperty("vote_count") int voteCount) {

    LocalDate parsedFirstAirDate;
    LocalDate parsedLastAirDate;
    try {
      parsedFirstAirDate = LocalDate.parse(firstAirDate, DateTimeFormatter.ISO_LOCAL_DATE);
      parsedLastAirDate = LocalDate.parse(lastAirDate, DateTimeFormatter.ISO_LOCAL_DATE);
    } catch (Exception e) {
      parsedFirstAirDate = null;
      parsedLastAirDate = null;
    }
    this.firstAirDate = parsedFirstAirDate;
    this.lastAirDate = parsedLastAirDate;

    this.backdropPath =
        posterPath != null ? "https://image.tmdb.org/t/p/original" + backdropPath : null;
    this.createdBy = createdBy;
    this.episodeRunTime = episodeRunTime;
    this.genres = genres;
    this.homepage = homepage;
    this.id = id;
    this.inProduction = inProduction;
    this.languages = languages;
    this.lastEpisodeToAir = lastEpisodeToAir;
    this.name = name;
    this.nextEpisodeToAir = nextEpisodeToAir;
    this.networks = networks;
    this.numberOfEpisodes = numberOfEpisodes;
    this.numberOfSeasons = numberOfSeasons;
    this.originCountry = originCountry;
    this.originalLanguage = originalLanguage;
    this.originalName = originalName;
    this.overview = overview;
    this.popularity = popularity;
    this.posterPath =
        posterPath != null ? "https://image.tmdb.org/t/p/original" + posterPath : null;
    this.seasons = seasons;
    this.status = status;
    this.type = type;
    this.voteAverage = voteAverage;
    this.voteCount = voteCount;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public List<CreatedByDTO> getCreatedBy() {
    return createdBy;
  }

  public List<Integer> getEpisodeRunTime() {
    return episodeRunTime;
  }

  public LocalDate getFirstAirDate() {
    return firstAirDate;
  }

  public List<GenreDTO> getGenres() {
    return genres;
  }

  public String getHomepage() {
    return homepage;
  }

  public Long getId() {
    return id;
  }

  public boolean getInProduction() {
    return inProduction;
  }

  public List<String> getLanguages() {
    return languages;
  }

  public LocalDate getLastAirDate() {
    return lastAirDate;
  }

  public TMDBEpisodeDTO getLastEpisodeToAir() {
    return lastEpisodeToAir;
  }

  public String getName() {
    return name;
  }

  public TMDBEpisodeDTO getNextEpisodeToAir() {
    return nextEpisodeToAir;
  }

  public List<NetworkDTO> getNetworks() {
    return networks;
  }

  public int getNumberOfEpisodes() {
    return numberOfEpisodes;
  }

  public int getNumberOfSeasons() {
    return numberOfSeasons;
  }

  public List<String> getOriginCountry() {
    return originCountry;
  }

  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public String getOriginalName() {
    return originalName;
  }

  public String getOverview() {
    return overview;
  }

  public double getPopularity() {
    return popularity;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public List<SeasonDTO> getSeasons() {
    return seasons;
  }

  public String getStatus() {
    return status;
  }

  public String getType() {
    return type;
  }

  public double getVoteAverage() {
    return voteAverage;
  }

  public int getVoteCount() {
    return voteCount;
  }

  @Override
  public String toString() {
    return getName() + " " + "(" + getFirstAirDate().getYear() + ")";
  }
}
