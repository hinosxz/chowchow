package com.centralesupelec.chowchow.user.service;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class UsersServiceImplementation implements UsersService {
    private UserRepository userRepository;

    @Autowired
    public UsersServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Async
    public CompletableFuture<Optional<UserEntity>> getUserById(Long id) {
        return this.userRepository
                .findById(id)
                .thenApply(Optional::ofNullable);
    }

    @Async
    public CompletableFuture<Optional<UserEntity>> getUserByUserName(String userName){
        return this.userRepository
                .findByUserName(userName)
                .thenApply(Optional::ofNullable);
    }

    @Async
    public CompletableFuture<Optional<UserEntity>> saveUser(UserEntity userEntity){
        return this.userRepository.save(userEntity).thenApply(Optional::ofNullable);
    }

}