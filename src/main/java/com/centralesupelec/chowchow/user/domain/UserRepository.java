package com.centralesupelec.chowchow.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
  UserEntity findById(final Integer id);

  UserEntity findByUsername(final String username);
}
