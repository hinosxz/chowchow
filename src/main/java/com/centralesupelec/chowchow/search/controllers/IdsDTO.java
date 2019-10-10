package com.centralesupelec.chowchow.search.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IdsDTO {
    private final int trakt;

    @JsonCreator
    public IdsDTO(@JsonProperty("id") int id) {
        this.trakt = id;
    }

    public int getTrakt() {
        return trakt;
    }
}
