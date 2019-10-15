package com.centralesupelec.chowchow.user.web;

import com.centralesupelec.chowchow.likes.controllers.ShowRatingDTO;
import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UsersWebController {

  private final UsersController usersController;

  private final Logger logger = LoggerFactory.getLogger(UsersController.class);

  @Autowired
  public UsersWebController(UsersController usersController) {
    this.usersController = usersController;
  }

  @RequestMapping(path = "", method = RequestMethod.POST)
  public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
    boolean result = this.usersController.createUser(userDTO);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @RequestMapping(
      path = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getUserById(@PathVariable(value = "id") Long userId) {
    return new ResponseEntity<>(this.usersController.getUserById(userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/like", method = RequestMethod.POST)
  public ResponseEntity likeShow(@RequestBody ShowRatingDTO showRatingDTO) {
    // TODO Chercher l'ID User dans la session
    Long userId = 1L;
    return new ResponseEntity<>(
        this.usersController.likeShow(showRatingDTO, userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/like/{id}", method = RequestMethod.DELETE)
  public ResponseEntity deleteMark(@PathVariable("id") Long showId) {
    // TODO Chercher l'ID User dans la session
    Long userId = 1L;
    return new ResponseEntity<>(this.usersController.unlikeShow(showId, userId), HttpStatus.OK);
  }
}
