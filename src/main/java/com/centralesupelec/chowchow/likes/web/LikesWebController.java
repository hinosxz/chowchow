package com.centralesupelec.chowchow.likes.web;

import com.centralesupelec.chowchow.lib.ShowIsAlreadyLikedException;
import com.centralesupelec.chowchow.lib.ShowIsNotLikedException;
import com.centralesupelec.chowchow.lib.UserNotFoundException;
import com.centralesupelec.chowchow.likes.controllers.LikeDTO;
import com.centralesupelec.chowchow.likes.controllers.LikesController;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/likes")
public class LikesWebController {

  private final LikesController likesController;

  @Autowired
  public LikesWebController(LikesController likesController) {
    this.likesController = likesController;
  }

  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<LikeDTO>> getLikedShows(HttpSession httpSession) {
    Integer userId = (Integer) httpSession.getAttribute("USER_ID");
    try {
      return new ResponseEntity<>(this.likesController.getLikedShows(userId), HttpStatus.OK);
    } catch (UserNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

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

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity updateMark(@RequestBody LikeDTO likeDTO, HttpSession httpSession) {
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

  @RequestMapping(path = "/likes/{id}", method = RequestMethod.DELETE)
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
