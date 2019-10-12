package com.centralesupelec.chowchow.showRating.domain;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.showRating.ShowRatingKey;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "ShowRatings")
public class ShowRatingEntity {
    @EmbeddedId
    private ShowRatingKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;

    @ManyToOne
    @MapsId("show_id")
    @JoinColumn(name="show_id")
    private ShowEntity show;

    @Enumerated
    private Mark mark;

    public static enum Mark {
        TERRIBLE,
        OK,
        GOOD,
        VERYGOOD,
        EXCELLENT
    }

    public UserEntity getUser() {
        return user;
    }

    public ShowEntity getShow() {
        return show;
    }

    public Mark getMark() {
        return mark;
    }

    public ShowRatingKey getId() {
        return id;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public void setShow(ShowEntity show) {
        this.show = show;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}
