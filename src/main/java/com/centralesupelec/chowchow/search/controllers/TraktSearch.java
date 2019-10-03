package com.centralesupelec.chowchow.search.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class Ids {
    private int trakt;

    public int getTrakt() {
        return trakt;
    }
}

@JsonIgnoreProperties(ignoreUnknown = true)
class TraktShow {
    private String title;
    private int year;
    private Ids ids;

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

@JsonIgnoreProperties(ignoreUnknown = true)
public class TraktSearch {
    private double score;
    private TraktShow show;

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
