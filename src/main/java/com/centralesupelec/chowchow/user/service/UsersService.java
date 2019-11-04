package com.centralesupelec.chowchow.user.service;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.domain.UserRepository;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsersService implements UserDetailsService {

  private final UserRepository userRepository;

  @Autowired
  public UsersService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<UserEntity> getUserById(Integer id) {
    return Optional.ofNullable(this.userRepository.findById(id));
  }

  public Optional<UserEntity> getUserByUsername(String username) {
    return Optional.ofNullable(this.userRepository.findByUsername(username));
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Objects.requireNonNull(username);
    return this.userRepository
        .findUserEntityByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  public Optional<UserEntity> saveUser(UserEntity userEntity) {
    return Optional.of(this.userRepository.save(userEntity));
  }
}
