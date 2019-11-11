package com.centralesupelec.chowchow.user.web;

import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UsersWebController {

  private final UsersController usersController;

  @Autowired
  public UsersWebController(UsersController usersController) {
    this.usersController = usersController;
  }

  // TODO: Do we still need this ?
  @ApiOperation(value = "Fetch a user using a given id")
  @RequestMapping(
      path = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer userId) {
    return this.usersController
        .getUserById(userId)
        .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
}
