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

/**
 * Transactional service implementing UserService contract interface and UserDetailsService
 * interfaces.
 *
 * @see UserService
 */
@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

  /** The UserRepository instance used to perform database transactions */
  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Retrieves a user with a given id.
   *
   * @see UserService#getUserById(Integer)
   */
  public Optional<UserEntity> getUserById(Integer userId) {
    return Optional.ofNullable(this.userRepository.findById(userId));
  }

  /**
   * Retrieves a user with a given username.
   *
   * @see UserService#getUserByUsername(String)
   */
  public Optional<UserEntity> getUserByUsername(String username) {
    return Optional.ofNullable(this.userRepository.findByUsername(username));
  }

  /**
   * Overrides the method from UserDetailsService to perform user authentication.
   *
   * <p>This method is used by Spring security to retrieve user with given username for
   * authentication
   *
   * @param username the given username
   * @throws UsernameNotFoundException
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Objects.requireNonNull(username);
    return this.userRepository
        .findUserEntityByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }

  /**
   * Saves a given user in the database.
   *
   * @see UserService#saveUser(UserEntity)
   */
  public Optional<UserEntity> saveUser(UserEntity userEntity) {
    return Optional.of(this.userRepository.save(userEntity));
  }
}
