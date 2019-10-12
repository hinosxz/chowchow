package com.centralesupelec.chowchow.showRating.controllers;

import com.centralesupelec.chowchow.showRating.domain.ShowRatingEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShowRatingDTO {

    private final Long id;
    private final Integer mark;

    @JsonBackReference
    private final Long userId;

    @JsonBackReference
    private final Long showId;

    @JsonCreator
    ShowRatingDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("user") Long user_id,
            @JsonProperty("show") Long show_id,
            @JsonProperty("mark") Integer mark
    ){
        this.userId = user_id;
        this.showId = show_id;
        this.mark = mark;
        this.id = id;
    }

    public Integer getMark() {
        return mark;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getShowId() {
        return showId;
    }

    public Long getId() {
        return id;
    }

    public static ShowRatingEntity toEntity(ShowRatingDTO showRatingDTO){
        ShowRatingEntity showRatingEntity = new ShowRatingEntity();
        // TODO faire les methodes dans l'entite pour creer un showRating
        return showRatingEntity;
    }

    public static ShowRatingDTO fromEntity(ShowRatingEntity showRatingEntity){
        return new ShowRatingDTO((long)1, (long)1, (long)1, 1);
    }

}
