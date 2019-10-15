package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.PremiumUserEntity;
import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserSignUpDTO extends UserSignInDTO {
    private final SubscriptionType subscriptionType;

    @JsonCreator
    public UserSignUpDTO(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("subscription_type") SubscriptionType subscriptionType
    ) {
        super(username, password);
        this.subscriptionType = subscriptionType;
    }

    public static UserEntity toEntity(UserSignUpDTO userSignUpDTO) {
        UserEntity userEntity = new UserEntity();
        if(!Objects.isNull(userSignUpDTO.subscriptionType)) {
            PremiumUserEntity premiumUserEntity = new PremiumUserEntity();
            premiumUserEntity.setSubscriptionType(userSignUpDTO.subscriptionType);
            userEntity = premiumUserEntity;
        }
        userEntity.setUsername(userSignUpDTO.getUsername());
        userEntity.setPassword(userSignUpDTO.getPassword());
        return userEntity;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }
}
