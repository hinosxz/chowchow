package com.centralesupelec.chowchow.showRating.domain;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.user.domain.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "ShowRatings")
public class ShowRatingEntity {
    @EmbeddedId
    ShowRatingEntity id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    UserEntity user;

    @ManyToOne
    @MapsId("show_id")
    @JoinColumn(name="show_id")
    ShowEntity show;

    @Enumerated
    public Mark mark;

    public static enum Mark {
        TERRIBLE,
        OK,
        GOOD,
        VERYGOOD,
        EXCELLENT
    }
}
