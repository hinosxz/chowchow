package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class UserDTO {
  private final Integer id;
  private final String username;
  private final SubscriptionType subscriptionType;

  @JsonCreator
  public UserDTO(
      @JsonProperty("id") Integer id,
      @JsonProperty("username") String username,
      @JsonProperty("subscriptionType") SubscriptionType subscriptionType) {
    this.id = id;
    this.username = username;
    this.subscriptionType = subscriptionType;
  }

  public static UserEntity toEntity(UserDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(userDTO.getUsername());
    return userEntity;
  }

  public static UserDTO fromEntity(UserEntity userEntity) {
    return new UserDTO(
        userEntity.getId(), userEntity.getUsername(), userEntity.getSubscriptionType());
  }

  public boolean isPremium() {
    return !Objects.isNull(subscriptionType);
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public SubscriptionType getSubscriptionType() {
    return subscriptionType;
  }
}
