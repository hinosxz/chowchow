package com.centralesupelec.chowchow.user.domain;

import com.centralesupelec.chowchow.lib.ShowIsAlreadyLikedException;
import com.centralesupelec.chowchow.lib.ShowIsNotLikedException;
import com.centralesupelec.chowchow.likes.domain.Like;
import com.centralesupelec.chowchow.likes.domain.Mark;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Users")
public class UserEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column private String username;

  @Column(nullable = false)
  private String password;

  @Column private SubscriptionType subscriptionType;

  @Column(unique = true, nullable = false)
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Like> likedShows;

  public Integer getId() {
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

  public void likeShow(Mark mark, Integer showId) throws ShowIsAlreadyLikedException {
    Optional<Like> maybeShowRating =
        this.likedShows.stream().filter(like -> Objects.equals(like.getShowId(), showId)).findAny();
    if (maybeShowRating.isPresent()) {
      throw new ShowIsAlreadyLikedException();
    }
    this.likedShows.add(new Like(this, showId, mark));
  }

  public void updateMark(Mark mark, Integer showId) throws ShowIsNotLikedException {
    Optional<Like> maybeShowRating =
        this.likedShows.stream().filter(like -> Objects.equals(like.getShowId(), showId)).findAny();
    if (!maybeShowRating.isPresent()) {
      throw new ShowIsNotLikedException();
    }
    maybeShowRating.get().setMark(mark);
  }

  public void unlikeShow(Integer showId) throws ShowIsNotLikedException {
    Optional<Like> maybeLikedShow =
        this.likedShows.stream().filter(like -> Objects.equals(like.getShowId(), showId)).findAny();
    if (!maybeLikedShow.isPresent()) {
      throw new ShowIsNotLikedException();
    }
    Like likedShow = maybeLikedShow.get();
    this.likedShows.remove(likedShow);
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }
}
