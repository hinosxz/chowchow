package com.centralesupelec.chowchow.user.domain;

import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.centralesupelec.chowchow.showRating.domain.ShowRatingEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="Users")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE")
@DiscriminatorValue("USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    @OneToMany(mappedBy = "user")
    Set<ShowRatingEntity> likedShows;

    private String username;

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

    public Set<ShowRatingEntity> getLikedShows() {return likedShows;}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLikedShows(Set<ShowRatingEntity> likedshows){this.likedShows = likedshows;}

    public void addLikedShows(ShowRatingEntity likedShow) {
        this.likedShows.add(likedShow);
    }
}
