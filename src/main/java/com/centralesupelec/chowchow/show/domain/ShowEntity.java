package com.centralesupelec.chowchow.show.domain;

import javax.persistence.*;

@Entity
@Table(name = "Shows")
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private int tmdb_id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int year;

    public Long getId() {
        return this.id;
    }

    public int getTMDBId() {
        return this.tmdb_id;
    }

    public void setTMDBId(int tmdbId) {
        this.tmdb_id = tmdbId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
