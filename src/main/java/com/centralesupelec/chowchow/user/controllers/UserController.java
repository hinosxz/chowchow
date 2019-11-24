package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.lib.UserAlreadyExistsException;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UserService;
import com.centralesupelec.chowchow.user.service.UserServiceImpl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public UserController(UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder) {
    this.userService = userServiceImpl;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<UserDTO> registerUser(RegisterUserDTO registerUserDTO)
      throws UserAlreadyExistsException {
    Optional<UserEntity> maybeUser =
        this.userService.getUserByUsername(registerUserDTO.getUsername());
    if (maybeUser.isPresent()) {
      throw new UserAlreadyExistsException();
    }
    UserEntity user = new UserEntity();
    System.out.println(this.passwordEncoder);
    user.setUsername(registerUserDTO.getUsername());
    user.setPassword(this.passwordEncoder.encode(registerUserDTO.getPassword()));
    user.setSubscriptionType(registerUserDTO.getSubscriptionType());
    userService.saveUser(user);
    return this.getUserById(user.getId());
  }

  public Optional<UserDTO> getUserById(Integer id) {
    return this.userService.getUserById(id).map(UserDTO::fromEntity);
  }
}
