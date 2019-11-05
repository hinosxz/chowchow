package com.centralesupelec.chowchow.user.web;

import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
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

  @RequestMapping(path = "/register", method = RequestMethod.POST)
  public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
    return new ResponseEntity<>(this.usersController.createUser(userDTO), HttpStatus.OK);
  }
}
