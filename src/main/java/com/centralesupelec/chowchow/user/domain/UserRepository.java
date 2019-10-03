package com.centralesupelec.chowchow.user.domain;
import org.springframework.data.repository.Repository;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;

public interface UserRepository extends Repository<UserEntity,String> {

    @Async
    CompletableFuture<UserEntity> findById(final Long id);

    @Async
    CompletableFuture<UserEntity> findByUserName(final String name);

    @Async
    CompletableFuture<UserEntity> save(final UserEntity userEntity);
}
