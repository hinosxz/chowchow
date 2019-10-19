package com.centralesupelec.chowchow.user.domain;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.showRating.domain.Mark;
import com.centralesupelec.chowchow.showRating.domain.ShowRatingEntity;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "Users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE")
@DiscriminatorValue("USER")
public class UserEntity implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true, nullable = false)
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<ShowRatingEntity> likedShows;

  @Column private String username;

  @Column(nullable = false)
  private String password;

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public Set<ShowRatingEntity> getLikedShows() {
    return likedShows;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setLikedShows(Set<ShowRatingEntity> likedShows) {
    this.likedShows = likedShows;
  }

  public void likeShow(Mark mark, ShowEntity show) {
    Optional<ShowRatingEntity> maybeShowRating =
        this.likedShows.stream()
            .filter(
                showRatingEntity ->
                    Objects.equals(showRatingEntity.getShow().getId(), show.getId()))
            .findAny();
    if (maybeShowRating.isPresent()) {
      maybeShowRating.get().setMark(mark);
    } else {
      this.likedShows.add(new ShowRatingEntity(this, show, mark));
    }
  }

  public void unlikeShow(Long showId) {
    this.likedShows.stream()
        .filter(showRatingEntity -> Objects.equals(showRatingEntity.getShow().getId(), showId))
        .findAny()
        .ifPresent(showRatingEntity -> this.likedShows.remove(showRatingEntity));
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
