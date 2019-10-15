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
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getGender() {
        return this.gender;
    }

    public String getProfilePath() {
        return this.profilePath;
    }

    @Override
    public String toString() {
        return this.getName();
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
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.getName();
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
        return this.id;
    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public String getName() {
        return this.name;
    }

    public String getOriginCountry() {
        return this.originCountry;
    }

    @Override
    public String toString() {
        return this.getName();
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
        return this.id;
    }

    public String getLogoPath() {
        return this.logoPath;
    }

    public String getName() {
        return this.name;
    }

    public String getOriginCountry() {
        return this.originCountry;
    }

    @Override
    public String toString() {
        return this.getName();
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
        return this.airDate;
    }

    public int getEpisodeCount() {
        return this.episodeCount;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getOverview() {
        return this.overview;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public int getSeasonNumber() {
        return this.seasonNumber;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}

public class TMDBShowDTO {
    private final String backdropPath;
    private final List<CreatedByDTO> createdBy;
    private final List<Integer> episodeRunTime;
    private final LocalDate firstAirDate;
    private final List<GenreDTO> genres;
    private final String homepage;
    private final int id;
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
            @JsonProperty("id") int id,
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
        return this.backdropPath;
    }

    public List<CreatedByDTO> getCreatedBy() {
        return this.createdBy;
    }

    public List<Integer> getEpisodeRunTime() {
        return this.episodeRunTime;
    }

    public LocalDate getFirstAirDate() {
        return this.firstAirDate;
    }

    public List<GenreDTO> getGenres() {
        return this.genres;
    }

    public String getHomepage() {
        return this.homepage;
    }

    public int getId() {
        return this.id;
    }

    public boolean getInProduction() {
        return this.inProduction;
    }

    public List<String> getLanguages() {
        return this.languages;
    }

    public LocalDate getLastAirDate() {
        return this.lastAirDate;
    }

    public TMDBEpisodeDTO getLastEpisodeToAir() {
        return this.lastEpisodeToAir;
    }

    public String getName() {
        return this.name;
    }

    public TMDBEpisodeDTO getNextEpisodeToAir() {
        return this.nextEpisodeToAir;
    }

    public List<NetworkDTO> getNetworks() {
        return this.networks;
    }

    public int getNumberOfEpisodes() {
        return this.numberOfEpisodes;
    }

    public int getNumberOfSeasons() {
        return this.numberOfSeasons;
    }

    public List<String> getOriginCountry() {
        return this.originCountry;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public String getOverview() {
        return this.overview;
    }

    public double getPopularity() {
        return this.popularity;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    public List<SeasonDTO> getSeasons() {
        return this.seasons;
    }

    public String getStatus() {
        return this.status;
    }

    public String getType() {
        return this.type;
    }

    public double getVoteAverage() {
        return this.voteAverage;
    }

    public int getVoteCount() {
        return this.voteCount;
    }

    @Override
    public String toString() {
        return this.getName() + " " + "(" + this.getFirstAirDate().getYear() + ")";
    }
}
