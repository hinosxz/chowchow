package com.centralesupelec.chowchow.showRating;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class showRatingKey implements Serializable {

    @Column(name="user_id")
    Long userId;

    @Column(name="show_id")
    Long showId;

    public showRatingKey() {
    }

    public showRatingKey(Long userId, Long showId) {
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
        if (!(o instanceof showRatingKey)) return false;
        showRatingKey that = (showRatingKey) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getShowId(), that.getShowId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getShowId());
    }


}
