package com.centralesupelec.chowchow.user.service;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import java.util.Optional;

/** Interface contracting the service handling user table operations in the database. */
public interface UserService {

  /**
   * Retrieves a user with a given id.
   *
   * @param userId the given user id
   * @return an optional wrapping the retrieved user
   */
  Optional<UserEntity> getUserById(Integer userId);

  /**
   * Retrieves a user with a given username.
   *
   * @param username the given user username
   * @return an optional wrapping the retrieved user
   */
  Optional<UserEntity> getUserByUsername(String username);

  /**
   * Saves a given user in the database.
   *
   * @param userEntity the given user
   * @return an optional wrapping the saved user
   */
  Optional<UserEntity> saveUser(UserEntity userEntity);
}
