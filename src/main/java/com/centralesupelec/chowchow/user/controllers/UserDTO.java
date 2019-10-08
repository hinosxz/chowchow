package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.PremiumUserEntity;
import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class UserDTO {

    private final Long id;
    private final String username;
    private final String password;
    private final SubscriptionType subscriptionType;

    @JsonCreator
    protected UserDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("subscriptionType") SubscriptionType subscriptionType
    ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.subscriptionType = subscriptionType;
    }

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        if(this.subscriptionType != null) {
            PremiumUserEntity premiumUserEntity = new PremiumUserEntity();
            premiumUserEntity.setSubscriptionType(this.subscriptionType);
            userEntity = premiumUserEntity;
        }
        userEntity.setUsername(this.getUsername());
        userEntity.setPassword(this.getPassword());
        return userEntity;
    };

    public static UserDTO fromEntity(UserEntity userEntity){
        return new UserDTO(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                null
        );
    }

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
}
