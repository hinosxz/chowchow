package com.centralesupelec.chowchow.showRating.domain;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import javax.persistence.*;

@Entity
@Table(
    name = "ShowRatings",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "show_id"}))
public class ShowRatingEntity {

  @EmbeddedId private ShowRatingKey id;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
  private UserEntity user;

  @ManyToOne
  @JoinColumn(name = "show_id", referencedColumnName = "id", insertable = false, updatable = false)
  private ShowEntity show;

  @Enumerated private Mark mark;

  public ShowRatingEntity() {}

  public ShowRatingEntity(UserEntity userEntity, ShowEntity showEntity, Mark mark) {
    this.id = new ShowRatingKey(userEntity, showEntity);
    this.user = userEntity;
    this.show = showEntity;
    this.mark = mark;
  }

  public ShowRatingKey getId() {
    return id;
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
