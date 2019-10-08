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

    public Optional<UserEntity> getUserById(Long id) {
        return Optional.ofNullable(this.userRepository
                .findById(id));
    }

    public Optional<UserEntity> getUserByUsername(String username){
        return Optional.ofNullable(this.userRepository
                .findByUsername(username));
    }

    public Optional<UserEntity> saveUser(UserEntity userEntity){
        return Optional.ofNullable(this.userRepository.save(userEntity));
    }

}