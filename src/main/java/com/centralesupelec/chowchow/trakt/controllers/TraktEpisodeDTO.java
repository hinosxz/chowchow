package com.centralesupelec.chowchow.trakt.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;

public class TraktEpisodeDTO {
    private final int season;
    private final int number;
    private final String title;
    private final IdsDTO idsDTO;
    private final Instant firstAired;

    @JsonCreator
    TraktEpisodeDTO(@JsonProperty("season") int season, @JsonProperty("number") int number, @JsonProperty("title") String title, @JsonProperty("ids") IdsDTO idsDTO, @JsonProperty("first_aired") String firstAired ) {
        this.season = season;
        this.number = number;
        this.title = title;
        this.idsDTO = idsDTO;
        this.firstAired = Instant.parse(firstAired);
    }

    public int getSeason() {
        return season;
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public IdsDTO getIdsDTO() {
        return idsDTO;
    }

    public Instant getFirstAired() {
        return firstAired;
    }

    @Override
    public String toString() {
        return getSeason() + "x" + getNumber() + ": " + getTitle();
    }
}
