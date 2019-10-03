package com.centralesupelec.chowchow.user.service;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public interface UsersService {
    public CompletableFuture<Optional<UserEntity>> getUserById(Long id);
    public CompletableFuture<Optional<UserEntity>> getUserByUserName(String userName);
    public CompletableFuture<Optional<UserEntity>> saveUser(UserEntity userEntity);
}
