package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterUserDTO {

  private final String username;
  private final String password;
  private final SubscriptionType subscriptionType;

  @JsonCreator
  public RegisterUserDTO(
      @JsonProperty("username") String username,
      @JsonProperty("password") String password,
      @JsonProperty("subscription_type") SubscriptionType subscriptionType) {
    this.username = username;
    this.password = password;
    this.subscriptionType = subscriptionType;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  SubscriptionType getSubscriptionType() {
    return subscriptionType;
  }
}
