package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.PremiumUserEntity;
import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class UserDTO {

  private final Long id;
  private final String username;
  private final SubscriptionType subscriptionType;

  @JsonCreator
  public UserDTO(
      @JsonProperty("id") Long id,
      @JsonProperty("username") String username,
      @JsonProperty("subscriptionType") SubscriptionType subscriptionType) {
    this.id = id;
    this.username = username;
    this.subscriptionType = subscriptionType;
  }

  public static UserEntity toEntity(UserDTO userDTO) {
    UserEntity userEntity = new UserEntity();
    if (userDTO.subscriptionType != null) {
      PremiumUserEntity premiumUserEntity = new PremiumUserEntity();
      premiumUserEntity.setSubscriptionType(userDTO.subscriptionType);
      userEntity = premiumUserEntity;
    }
    userEntity.setUsername(userDTO.getUsername());
    return userEntity;
  }

  public static UserDTO fromEntity(UserEntity userEntity) {
    SubscriptionType subscriptionType = null;
    if (userEntity.getClass() == PremiumUserEntity.class) {
      subscriptionType = ((PremiumUserEntity) userEntity).getSubscriptionType();
    }
    return new UserDTO(userEntity.getId(), userEntity.getUsername(), subscriptionType);
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
}
