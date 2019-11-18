package com.centralesupelec.chowchow.likes.web;

import com.centralesupelec.chowchow.lib.ShowIsAlreadyLikedException;
import com.centralesupelec.chowchow.lib.ShowIsNotLikedException;
import com.centralesupelec.chowchow.lib.UserNotFoundException;
import com.centralesupelec.chowchow.likes.controllers.LikeDTO;
import com.centralesupelec.chowchow.likes.controllers.LikesController;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping(path = "/likes")
public class LikesWebController {

  private final LikesController likesController;

  @Autowired
  public LikesWebController(LikesController likesController) {
    this.likesController = likesController;
  }

  @ApiOperation(
      value = "Get the shows liked by the logged user",
      notes = "The user is retrieved using the logged user id.")
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LikeDTO>> getLikedShows(HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    try {
      return new ResponseEntity<>(this.likesController.getLikedShows(userId), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @ApiOperation(
      value = "Get a specific show liked by the logged user",
      notes = "The user is retrieved using the logged user id.")
  @RequestMapping(
      path = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getLikedShow(@PathVariable("id") Integer showId, HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    try {
      return new ResponseEntity<>(this.likesController.getLikedShow(showId, userId), HttpStatus.OK);
    } catch (HttpClientErrorException error) {
      return ResponseEntity.status(error.getStatusCode()).body(error.getMessage());
    }
  }

  @ApiOperation(
      value = "Add a show with a given id to the logged user's liked shows",
      notes =
          "The user is retrieved using the logged user id. A mark between 0 and 2 (included) can be provided to set a rating.")
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity likeShow(@RequestBody LikeDTO likeDTO, HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    try {
      this.likesController.likeShow(likeDTO, userId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (ShowIsAlreadyLikedException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @ApiOperation(
      value = "Update the mark of a given show liked by the logged user",
      notes =
          " The user is retrieved using the logged user id. The mark must be between 0 and 2 (included).")
  @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
  public ResponseEntity updateMark(
      @RequestBody LikeDTO likeDTO, HttpSession httpSession, @PathVariable("id") Integer showId) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    try {
      this.likesController.updateMark(likeDTO, userId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (ShowIsNotLikedException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @ApiOperation(
      value = "Delete a show with a given id from the logged user's liked shows",
      notes = "The user is retrieved using the logged user id.")
  @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity unlikeShow(@PathVariable("id") Integer showId, HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    try {
      this.likesController.unlikeShow(showId, userId);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    } catch (ShowIsNotLikedException e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}
