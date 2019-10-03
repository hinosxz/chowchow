package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    private final Long id;
    private final String userName;
    private final String password;

    @JsonCreator
    UserDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("userName") String userName,
            @JsonProperty("password") String password
    ){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(userDTO.password);
        userEntity.setUserName(userDTO.userName);
        return userEntity;
    }

    public static UserDTO fromEntity(UserEntity userEntity){
        return new UserDTO(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword());
    }
}
