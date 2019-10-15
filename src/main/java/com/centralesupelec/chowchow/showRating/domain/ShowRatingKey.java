package com.centralesupelec.chowchow.showRating.domain;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class ShowRatingKey implements Serializable {

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "show_id")
  private Long showId;

  public ShowRatingKey() {}

  public ShowRatingKey(UserEntity userEntity, ShowEntity showEntity) {
    this.userId = userEntity.getId();
    this.showId = showEntity.getId();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    ShowRatingKey that = (ShowRatingKey) o;

    return new EqualsBuilder()
        .append(this.userId, that.userId)
        .append(this.showId, that.showId)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37).append(this.userId).append(this.showId).toHashCode();
  }
}
