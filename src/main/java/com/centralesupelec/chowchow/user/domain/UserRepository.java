package com.centralesupelec.chowchow.user.domain;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserEntity,String> {

    UserEntity findById(final Long id);

    UserEntity findByUsername(final String username);

    UserEntity save(final UserEntity userEntity);
}