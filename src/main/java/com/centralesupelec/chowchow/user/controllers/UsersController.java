package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.lib.UserAlreadyExistsException;
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

  public Optional<UserDTO> registerUser(RegisterUserDTO registerUserDTO)
      throws UserAlreadyExistsException {
    Optional<UserEntity> maybeUser =
        this.usersService.getUserByUsername(registerUserDTO.getUsername());
    if (maybeUser.isPresent()) {
      throw new UserAlreadyExistsException();
    }
    UserEntity user = new UserEntity();
    user.setUsername(registerUserDTO.getUsername());
    user.setPassword(registerUserDTO.getPassword());
    user.setSubscriptionType(registerUserDTO.getSubscriptionType());
    usersService.saveUser(user);
    return this.getUserById(user.getId());
  }

  public Optional<UserDTO> getUserById(Integer id) {
    return this.usersService.getUserById(id).map(UserDTO::fromEntity);
  }

  public boolean getUpcomingEpisodesForUser(Integer userId) {
    return true;
  }
}
