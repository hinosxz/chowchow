package com.centralesupelec.chowchow.user.service;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;

    @Autowired
    public UsersServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        return Optional.ofNullable(this.userRepository.findById(id));
    }

    @Override
    public Optional<UserEntity> getUserByUsername(String username) {
        return Optional.ofNullable(this.userRepository.findByUsername(username));
    }

    @Override
    public Optional<UserEntity> saveUser(UserEntity userEntity) {
        return Optional.ofNullable(this.userRepository.save(userEntity));
    }
}
