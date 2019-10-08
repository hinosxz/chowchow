package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    private final Long id;
    private final String username;
    private final String password;

    @JsonCreator
    UserDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("username") String username,
            @JsonProperty("password") String password
    ){
        this.id = id;
        this.username = username;
        this.password = password;
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

    public static UserEntity toEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(userDTO.password);
        userEntity.setUsername(userDTO.username);
        return userEntity;
    }

    public static UserDTO fromEntity(UserEntity userEntity){
        return new UserDTO(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword());
    }
}
