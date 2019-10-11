package com.centralesupelec.chowchow.show.domain;

import javax.persistence.*;

@Entity
@Table(name = "Shows")
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int traktId;

    private String name;

    public Long getId() {
        return id;
    }

    public int getTraktId() {
        return traktId;
    }

    public void setTraktId(int traktId) {
        this.traktId = traktId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
