package com.centralesupelec.chowchow.search.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TraktShowDTO {
    private final String title;
    private final int year;
    private final IdsDTO idsDTO;
    private final String overview;
    private final String trailer;
    private final String homepage;
    private final List<String> genres;
    private final int airedEpisodes;

    @JsonCreator
    TraktShowDTO(
            @JsonProperty("title") String title,
            @JsonProperty("year") int year,
            @JsonProperty("ids") IdsDTO idsDTO,
            @JsonProperty("overview") String overview,
            @JsonProperty("trailer") String trailer,
            @JsonProperty("homepage") String homepage,
            @JsonProperty("genres") List<String> genres,
            @JsonProperty("airedEpisodes") int airedEpisodes
    ) {
        this.title = title;
        this.year = year;
        this.idsDTO = idsDTO;
        this.overview = overview;
        this.trailer = trailer;
        this.homepage = homepage;
        this.genres = genres;
        this.airedEpisodes = airedEpisodes;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public IdsDTO getIdsDTO() {
        return idsDTO;
    }

    public String getOverview() {
        return overview;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getHomepage() {
        return homepage;
    }

    public List<String> getGenres() {
        return genres;
    }

    public int getAiredEpisodes() {
        return airedEpisodes;
    }
}
