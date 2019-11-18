package com.centralesupelec.chowchow.likes.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
import com.centralesupelec.chowchow.lib.ShowIsAlreadyLikedException;
import com.centralesupelec.chowchow.lib.ShowIsNotLikedException;
import com.centralesupelec.chowchow.lib.UserNotFoundException;
import com.centralesupelec.chowchow.likes.domain.Like;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;

@Controller
public class LikesController {
  private final Logger LOGGER = LoggerFactory.getLogger(LikesController.class);

  private final UsersService usersService;
  private final SearchService searchService;

  @Autowired
  public LikesController(UsersService usersService, SearchService searchService) {
    this.usersService = usersService;
    this.searchService = searchService;
  }

  public List<LikeDTO> getLikedShows(Integer id) throws UserNotFoundException {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(id);
    if (!maybeUser.isPresent()) {
      throw new UserNotFoundException();
    }
    List<CompletableFuture<LikeDTO>> likeDTOPromises =
        maybeUser.get().getLikedShows().stream()
            .map(
                like ->
                    this.searchService
                        .findShowById(like.getShowId())
                        .thenApply(tmdbShowDTO -> new LikeDTO(like.getMark(), tmdbShowDTO)))
            .collect(Collectors.toList());

    return likeDTOPromises.stream().map(CompletableFuture::join).collect(Collectors.toList());
  }

  public LikeDTO getLikedShow(Integer showId, Integer userId) throws HttpClientErrorException {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
    }

    // Try to find the selected show in the user's likes
    CompletableFuture<LikeDTO> likeDTOPromise = null;
    int i = 0;
    List<Like> likes = maybeUser.get().getLikedShows();
    while (likeDTOPromise == null && i < likes.size()) {
      Like like = likes.get(i);
      if (like.getShowId().equals(showId)) {
        likeDTOPromise =
            this.searchService
                .findShowById(like.getShowId())
                .thenApply(tmdbShowDTO -> new LikeDTO(like.getMark(), tmdbShowDTO));
      }
      i++;
    }

    if (likeDTOPromise == null) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    return likeDTOPromise.join();
  }

  public void likeShow(LikeDTO likeDTO, Integer userId)
      throws UserNotFoundException, ShowIsAlreadyLikedException {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      throw new UserNotFoundException();
    }
    UserEntity user = maybeUser.get();
    user.likeShow(likeDTO.getMark(), likeDTO.getShow().getId());
    this.usersService.saveUser(user);
  }

  public void updateMark(Integer showId, LikeDTO likeDTO, Integer userId)
      throws UserNotFoundException, ShowIsNotLikedException {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      throw new UserNotFoundException();
    }
    UserEntity user = maybeUser.get();
    user.updateMark(likeDTO.getMark(), showId);
    this.usersService.saveUser(user);
  }

  public void unlikeShow(Integer showId, Integer userId)
      throws UserNotFoundException, ShowIsNotLikedException {
    Optional<UserEntity> maybeUserDTO = this.usersService.getUserById(userId);
    if (!maybeUserDTO.isPresent()) {
      throw new UserNotFoundException();
    }
    UserEntity user = maybeUserDTO.get();
    user.unlikeShow(showId);
    this.usersService.saveUser(user);
  }
}
