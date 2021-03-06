package com.centralesupelec.chowchow.likes.domain;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Embeddable
public class LikeKey implements Serializable {

  @Column(name = "user_id")
  private Integer userId;

  @Column(name = "show_id")
  private Integer showId;

  public LikeKey() {}

  public LikeKey(UserEntity userEntity, Integer showId) {
    this.userId = userEntity.getId();
    this.showId = showId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }

    LikeKey that = (LikeKey) o;

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
