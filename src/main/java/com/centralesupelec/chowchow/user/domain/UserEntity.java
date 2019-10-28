package com.centralesupelec.chowchow.user.domain;

import com.centralesupelec.chowchow.likes.domain.Like;
import com.centralesupelec.chowchow.likes.domain.Mark;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column private String username;

  @Column(nullable = false)
  private String password;

  @Column private SubscriptionType subscriptionType;

  @Column(unique = true, nullable = false)
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Like> likedShows;

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public SubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  public List<Like> getLikedShows() {
    return likedShows;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setSubscriptionType(SubscriptionType subscriptionType) {
    this.subscriptionType = subscriptionType;
  }

  public void setLikedShows(List<Like> likedShows) {
    this.likedShows = likedShows;
  }

  public boolean likeShow(Mark mark, Long showId) {
    Optional<Like> maybeShowRating =
        this.likedShows.stream().filter(like -> Objects.equals(like.getShowId(), showId)).findAny();
    if (maybeShowRating.isPresent()) {
      return false;
    } else {
      this.likedShows.add(new Like(this, showId, mark));
      return true;
    }
  }

  public boolean updateMark(Mark mark, Long showId) {
    Optional<Like> maybeShowRating =
        this.likedShows.stream().filter(like -> Objects.equals(like.getShowId(), showId)).findAny();
    if (maybeShowRating.isPresent()) {
      maybeShowRating.get().setMark(mark);
      return true;
    } else {
      return false;
    }
  }

  public void unlikeShow(Long showId) {
    this.likedShows.stream()
        .filter(like -> Objects.equals(like.getShowId(), showId))
        .findAny()
        .ifPresent(like -> this.likedShows.remove(like));
  }
}
