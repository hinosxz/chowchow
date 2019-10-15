package com.centralesupelec.chowchow.TMDB.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TMDBSearchShowDTO {
    private final String originalName;
    private final List<Integer> genreIds;
    private final String name;
    private final double popularity;
    private final List<String> originCountry;
    private final int voteCount;
    private final LocalDate firstAirDate;
    private final String backdropPath;
    private final String originalLanguage;
    private final int id;
    private final double voteAverage;
    private final String overview;
    private final String posterPath;

    @JsonCreator
    TMDBSearchShowDTO(
            @JsonProperty("original_name") String originalName,
            @JsonProperty("genre_ids") List<Integer> genreIds,
            @JsonProperty("name") String name,
            @JsonProperty("popularity") double popularity,
            @JsonProperty("origin_country") List<String> originCountry,
            @JsonProperty("vote_count") int voteCount,
            @JsonProperty("first_air_date") String firstAirDate,
            @JsonProperty("backdrop_path") String backdropPath,
            @JsonProperty("original_language") String originalLanguage,
            @JsonProperty("id") int id,
            @JsonProperty("vote_average") double voteAverage,
            @JsonProperty("overview") String overview,
            @JsonProperty("poster_path") String posterPath) {
        LocalDate parsedFirstAirDate;
        try {
            parsedFirstAirDate = LocalDate.parse(firstAirDate, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (Exception e) {
            parsedFirstAirDate = null;
        }
        this.firstAirDate = parsedFirstAirDate;

        this.originalName = originalName;
        this.genreIds = genreIds;
        this.name = name;
        this.popularity = popularity;
        this.originCountry = originCountry;
        this.voteCount = voteCount;
        this.backdropPath =
                posterPath != null ? "https://image.tmdb.org/t/p/original" + backdropPath : null;
        this.originalLanguage = originalLanguage;
        this.id = id;
        this.voteAverage = voteAverage;
        this.overview = overview;
        this.posterPath =
                posterPath != null ? "https://image.tmdb.org/t/p/original" + posterPath : null;
    }

    public String getOriginalName() {
        return this.originalName;
    }

    public List<Integer> getGenreIds() {
        return this.genreIds;
    }

    public String getName() {
        return this.name;
    }

    public double getPopularity() {
        return this.popularity;
    }

    public List<String> getOriginCountry() {
        return this.originCountry;
    }

    public int getVoteCount() {
        return this.voteCount;
    }

    public LocalDate getFirstAirDate() {
        return this.firstAirDate;
    }

    public String getBackdropPath() {
        return this.backdropPath;
    }

    public String getOriginalLanguage() {
        return this.originalLanguage;
    }

    public int getId() {
        return this.id;
    }

    public double getVoteAverage() {
        return this.voteAverage;
    }

    public String getOverview() {
        return this.overview;
    }

    public String getPosterPath() {
        return this.posterPath;
    }

    @Override
    public String toString() {
        return this.getName() + " " + "(" + this.getFirstAirDate().getYear() + ")";
    }
}
