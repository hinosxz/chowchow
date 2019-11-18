package com.centralesupelec.chowchow.user.web;

import com.centralesupelec.chowchow.lib.UserAlreadyExistsException;
import com.centralesupelec.chowchow.user.controllers.RegisterUserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "")
public class AuthWebController {

  private final UsersController usersController;

  @Autowired
  public AuthWebController(UsersController usersController) {
    this.usersController = usersController;
  }

  @ApiOperation(
      value = "Register a new user",
      notes =
          "The newly created user will have the given username, password and subscription type.")
  @RequestMapping(path = "/register", method = RequestMethod.POST)
  public ResponseEntity registerUser(@RequestBody RegisterUserDTO registerUserDTO) {
    try {
      return new ResponseEntity<>(
          this.usersController.registerUser(registerUserDTO), HttpStatus.OK);
    } catch (UserAlreadyExistsException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
