package com.centralesupelec.chowchow.show.controllers;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ShowDTO {
    private final Long id;
    private final Long traktId;
    private final String name;

    @JsonCreator
    ShowDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("traktId") Long traktId,
            @JsonProperty("name") String name
    ) {
        this.id = id;
        this.traktId = traktId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Long getTraktId() {
        return traktId;
    }

    public String getName() {
        return name;
    }

    public static ShowDTO fromEntity(ShowEntity showEntity) {
        return new ShowDTO(showEntity.getId(), showEntity.getTraktId(), showEntity.getName());
    }

    public static ShowEntity toEntity(ShowDTO showDTO) {
        ShowEntity showEntity = new ShowEntity();
        showEntity.setTraktId(showDTO.traktId);
        showEntity.setName(showDTO.name);
        return showEntity;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        ShowDTO that = (ShowDTO) object;
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
