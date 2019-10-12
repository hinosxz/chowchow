package com.centralesupelec.chowchow.showRating;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShowRatingKey implements Serializable {

    @Column(name="user_id")
    Long userId;

    @Column(name="show_id")
    Long showId;

    public ShowRatingKey() {
    }

    public ShowRatingKey(Long userId, Long showId) {
        this.userId = userId;
        this.showId = showId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getShowId() {
        return showId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShowRatingKey)) return false;
        ShowRatingKey that = (ShowRatingKey) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getShowId(), that.getShowId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getShowId());
    }


}
