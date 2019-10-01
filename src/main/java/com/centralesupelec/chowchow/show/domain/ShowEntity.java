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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ShowEntity that = (ShowEntity) object;
        return new EqualsBuilder()
                .append(this.id, that.id)
                .append(this.traktId, that.traktId)
                .append(this.name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(this.id)
                .append(this.traktId)
                .append(this.name)
                .toHashCode();
    }
}
