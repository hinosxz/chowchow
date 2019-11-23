package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.lib.UserAlreadyExistsException;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UserServiceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {

  private final UserServiceImpl userServiceImpl;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UsersController(UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder) {
    this.userServiceImpl = userServiceImpl;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<UserDTO> registerUser(RegisterUserDTO registerUserDTO)
      throws UserAlreadyExistsException {
    Optional<UserEntity> maybeUser =
        this.userServiceImpl.getUserByUsername(registerUserDTO.getUsername());
    if (maybeUser.isPresent()) {
      throw new UserAlreadyExistsException();
    }
    UserEntity user = new UserEntity();
    System.out.println(this.passwordEncoder);
    user.setUsername(registerUserDTO.getUsername());
    user.setPassword(this.passwordEncoder.encode(registerUserDTO.getPassword()));
    user.setSubscriptionType(registerUserDTO.getSubscriptionType());
    userServiceImpl.saveUser(user);
    return this.getUserById(user.getId());
  }

  public Optional<UserDTO> getUserById(Integer id) {
    return this.userServiceImpl.getUserById(id).map(UserDTO::fromEntity);
  }

  public boolean getUpcomingEpisodesForUser(Integer userId) {
    return true;
  }
}
