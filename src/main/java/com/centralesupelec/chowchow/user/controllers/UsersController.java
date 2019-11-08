package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UsersController {

  private final UsersService usersService;

  @Autowired
  public UsersController(UsersService usersService) {
    this.usersService = usersService;
  }

  public boolean registerUser(RegisterUserDTO registerUserDTO) {
    Optional<UserEntity> maybeUser =
        this.usersService.getUserByUsername(registerUserDTO.getUsername());
    if (maybeUser.isPresent()) {
      return false;
    }
    UserEntity user = new UserEntity();
    user.setUsername(registerUserDTO.getUsername());
    user.setPassword(registerUserDTO.getPassword());
    user.setSubscriptionType(registerUserDTO.getSubscriptionType());
    usersService.saveUser(user);
    return true;
  }

  public Optional<UserDTO> getUserById(Integer id) {
    return this.usersService.getUserById(id).map(UserDTO::fromEntity);
  }

  public boolean getUpcomingEpisodesForUser(Integer userId) {
    return true;
  }
}
