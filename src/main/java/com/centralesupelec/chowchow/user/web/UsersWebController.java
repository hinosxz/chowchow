package com.centralesupelec.chowchow.user.web;

import com.centralesupelec.chowchow.likes.controllers.LikeDTO;
import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
import java.util.List;
import javax.servlet.http.HttpSession;
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
  public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Integer userId) {
    return this.usersController
        .getUserById(userId)
        .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @RequestMapping(
      path = "/likes",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LikeDTO>> getLikedShows(HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    return new ResponseEntity<>(this.usersController.getLikedShows(userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/likes", method = RequestMethod.POST)
  public ResponseEntity likeShow(@RequestBody LikeDTO likeDTO, HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    return new ResponseEntity<>(this.usersController.likeShow(likeDTO, userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/likes", method = RequestMethod.PUT)
  public ResponseEntity updateMark(@RequestBody LikeDTO likeDTO, HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    return new ResponseEntity<>(this.usersController.updateMark(likeDTO, userId), HttpStatus.OK);
  }

  @RequestMapping(path = "/likes/{id}", method = RequestMethod.DELETE)
  public ResponseEntity unlikeShow(@PathVariable("id") Integer showId, HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    return new ResponseEntity<>(this.usersController.unlikeShow(showId, userId), HttpStatus.OK);
  }
}
