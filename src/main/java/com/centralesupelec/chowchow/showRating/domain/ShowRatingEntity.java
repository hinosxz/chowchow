package com.centralesupelec.chowchow.showRating.domain;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.showRating.ShowRatingKey;
import com.centralesupelec.chowchow.user.domain.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "ShowRatings", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "show_id"}))
public class ShowRatingEntity {

    @EmbeddedId
    private ShowRatingKey id;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name="show_id")
    private ShowEntity show;

    @Enumerated
    private Mark mark;

    public static enum Mark {
        TERRIBLE,
        OK,
        GOOD,
        VERYGOOD,
        EXCELLENT}

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
