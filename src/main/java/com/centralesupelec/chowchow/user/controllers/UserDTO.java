package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.likes.controllers.ShowRatingDTO;
import com.centralesupelec.chowchow.likes.domain.ShowRatingEntity;
import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class UserDTO {
  private final Long id;
  private final String username;
  private final SubscriptionType subscriptionType;
  private final List<ShowRatingDTO> likedShows;

  @JsonCreator
  public UserDTO(
      @JsonProperty("id") Long id,
      @JsonProperty("username") String username,
      @JsonProperty("subscriptionType") SubscriptionType subscriptionType,
      @JsonProperty("likedShows") List<ShowRatingDTO> likedShows) {
    this.id = id;
    this.username = username;
    this.subscriptionType = subscriptionType;
    this.likedShows = likedShows;
  }

  public static UserEntity toEntity(UserDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(userDTO.getUsername());
    userEntity.setLikedShows(
        userDTO.likedShows.stream()
            .map(
                likedShow -> {
                  ShowRatingEntity showRating = ShowRatingDTO.toEntity(likedShow);
                  showRating.setUser(userEntity);
                  return showRating;
                })
            .collect(Collectors.toList()));
    return userEntity;
  }

  public static UserDTO fromEntity(UserEntity userEntity) {
    return new UserDTO(
        userEntity.getId(),
        userEntity.getUsername(),
        userEntity.getSubscriptionType(),
        userEntity.getLikedShows().stream()
            .map(likedShow -> ShowRatingDTO.fromEntity(likedShow))
            .collect(Collectors.toList()));
  }

  public boolean isPremium() {
    return !Objects.isNull(subscriptionType);
  }

  public Long getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public SubscriptionType getSubscriptionType() {
    return subscriptionType;
  }

  public List<ShowRatingDTO> getLikedShows() {
    return likedShows;
  }
}
