package com.centralesupelec.chowchow.likes.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
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

  public List<LikeDTO> getLikedShows(Integer id) {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(id);
    if (!maybeUser.isPresent()) {
      return null;
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

  public boolean likeShow(LikeDTO likeDTO, Integer userId) {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      LOGGER.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUser.get();
    boolean success = user.likeShow(likeDTO.getMark(), likeDTO.getShow().getId());
    this.usersService.saveUser(user);
    return success;
  }

  public boolean updateMark(Integer showId, LikeDTO likeDTO, Integer userId) {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      LOGGER.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUser.get();
    boolean success = user.updateMark(likeDTO.getMark(), showId);
    this.usersService.saveUser(user);
    return success;
  }

  public boolean unlikeShow(Integer showId, Integer userId) {
    Optional<UserEntity> maybeUserDTO = this.usersService.getUserById(userId);
    if (!maybeUserDTO.isPresent()) {
      LOGGER.warn("Unsuccessful attempts to find user with id {}", userId);
      return false;
    }
    UserEntity user = maybeUserDTO.get();
    user.unlikeShow(showId);
    this.usersService.saveUser(user);
    return true;
  }
}
