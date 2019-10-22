package com.centralesupelec.chowchow.auth.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class AuthController {

  private final UsersService usersService;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public AuthController(UsersService usersService, PasswordEncoder passwordEncoder) {
    this.usersService = usersService;
    this.passwordEncoder = passwordEncoder;
  }

  public boolean signUp(CreateUserDTO createUserDTO) {
    Optional<UserEntity> maybeUser =
        this.usersService.getUserByUsername(createUserDTO.getUsername());
    if (maybeUser.isPresent()) {
      return false;
    }
    UserEntity user = new UserEntity();
    user.setUsername(createUserDTO.getUsername());
    System.out.println(createUserDTO.getUsername());
    user.setPassword(this.passwordEncoder.encode(createUserDTO.getPassword()));
    return usersService.saveUser(user).isPresent();
  }
}
