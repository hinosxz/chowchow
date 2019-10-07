package com.centralesupelec.chowchow.search.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class Ids {
    private final int trakt;

    @JsonCreator
    Ids(@JsonProperty("id") int id) {
        this.trakt = id;
    }

    public int getTrakt() {
        return trakt;
    }
}

class TraktShow {
    private final String title;
    private final int year;
    private final Ids ids;

    @JsonCreator
    TraktShow(@JsonProperty("title") String title, @JsonProperty("year") int year, @JsonProperty("ids") Ids ids) {
        this.title = title;
        this.year = year;
        this.ids = ids;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Ids getIds() {
        return ids;
    }

    @Override
    public String toString() {
        return getIds().getTrakt() + ": " + getTitle();
    }
}

public class TraktSearch {
    private final double score;
    private final TraktShow show;

    @JsonCreator
    TraktSearch(@JsonProperty("score") double score, @JsonProperty("show") TraktShow show) {
        this.score = score;
        this.show = show;
    }

    public double getScore() {
        return score;
    }

    public TraktShow getShow() {
        return show;
    }

    @Override
    public String toString() {
        return getShow().toString();
    }
}
