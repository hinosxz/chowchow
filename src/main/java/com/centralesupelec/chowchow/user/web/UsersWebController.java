package com.centralesupelec.chowchow.user.web;

import com.centralesupelec.chowchow.likes.controllers.ShowRatingDTO;
import com.centralesupelec.chowchow.user.controllers.LikedShowDTO;
import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
import java.util.List;
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

  @RequestMapping(path = "", method = RequestMethod.POST)
  public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
    return new ResponseEntity<>(this.usersController.createUser(userDTO), HttpStatus.OK);
  }

  @RequestMapping(
      path = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId) {
    return this.usersController
        .getUserById(userId)
        .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RequestMapping(
      path = "/likes",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LikedShowDTO>> getLikedShows() {
    // TODO Chercher l'ID User dans la session
    Long userId = 1L;
    return new ResponseEntity<>(this.usersController.getLikedShows(userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/likes", method = RequestMethod.POST)
  public ResponseEntity likeShow(@RequestBody ShowRatingDTO showRatingDTO) {
    // TODO Chercher l'ID User dans la session
    Long userId = 1L;
    return new ResponseEntity<>(
        this.usersController.likeShow(showRatingDTO, userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/likes", method = RequestMethod.PUT)
  public ResponseEntity updateMark(@RequestBody ShowRatingDTO showRatingDTO) {
    // TODO Chercher l'ID User dans la session
    Long userId = 1L;
    return new ResponseEntity<>(
        this.usersController.updateMark(showRatingDTO, userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/likes/{id}", method = RequestMethod.DELETE)
  public ResponseEntity unlikeShow(@PathVariable("id") Long showId) {
    // TODO Chercher l'ID User dans la session
    Long userId = 1L;
    return new ResponseEntity<>(this.usersController.unlikeShow(showId, userId), HttpStatus.OK);
  }
}
