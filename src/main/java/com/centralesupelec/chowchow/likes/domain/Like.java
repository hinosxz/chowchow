package com.centralesupelec.chowchow.likes.domain;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import javax.persistence.*;

@Entity
@Table(name = "Likes", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "show_id"}))
public class Like {

  @EmbeddedId private LikeKey id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  private UserEntity user;

  @Column(name = "show_id", insertable = false, updatable = false)
  private Integer showId;

  @Enumerated private Mark mark;

  public Like() {}

  public Like(UserEntity userEntity, Integer showId, Mark mark) {
    this.id = new LikeKey(userEntity, showId);
    this.user = userEntity;
    this.showId = showId;
    this.mark = mark;
  }

  public LikeKey getId() {
    return id;
  }

  public UserEntity getUser() {
    return user;
  }

  public Integer getShowId() {
    return showId;
  }

  public Mark getMark() {
    return mark;
  }

  public void setUser(UserEntity user) {
    this.user = user;
  }

  public void setShowId(Integer showId) {
    this.showId = showId;
  }

  public void setMark(Mark mark) {
    this.mark = mark;
  }
}
