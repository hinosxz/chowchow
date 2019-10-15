package com.centralesupelec.chowchow.user.service;

import com.centralesupelec.chowchow.user.domain.UserEntity;

import java.util.Optional;

public interface UsersService {
    public Optional<UserEntity> getUserById(Long id);

    public Optional<UserEntity> getUserByUsername(String username);

    public Optional<UserEntity> saveUser(UserEntity userEntity);
}
