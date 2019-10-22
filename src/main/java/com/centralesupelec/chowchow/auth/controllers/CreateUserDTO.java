package com.centralesupelec.chowchow.auth.controllers;

import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateUserDTO {

  private final String username;
  private final String password;
  private final SubscriptionType subscriptionType;

  @JsonCreator
  public CreateUserDTO(
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

  public SubscriptionType getSubscriptionType() {
    return subscriptionType;
  }
}
