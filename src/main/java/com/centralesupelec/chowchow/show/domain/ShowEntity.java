package com.centralesupelec.chowchow.show.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "Shows")
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String traktId;

    private String name;

    public Long getId() {
        return id;
    }

    public String getTraktId() {
        return traktId;
    }

    public void setTraktId(String traktId) {
        this.traktId = traktId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
