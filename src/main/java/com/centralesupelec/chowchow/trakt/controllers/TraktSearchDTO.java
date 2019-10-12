package com.centralesupelec.chowchow.trakt.controllers;

import com.centralesupelec.chowchow.trakt.controllers.TraktShowDTO;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TraktSearchDTO {
    private final double score;
    private final TraktShowDTO traktShowDTO;

    @JsonCreator
    TraktSearchDTO(
            @JsonProperty("score") double score,
            @JsonProperty("show") TraktShowDTO traktShowDTO
    ) {
        this.score = score;
        this.traktShowDTO = traktShowDTO;
    }

    public double getScore() {
        return score;
    }

    public TraktShowDTO getTraktShowDTO() {
        return traktShowDTO;
    }

    @Override
    public String toString() {
        return getTraktShowDTO().toString();
    }
}
