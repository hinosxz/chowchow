package com.centralesupelec.chowchow.likes.controllers;

import com.centralesupelec.chowchow.TMDB.service.SearchService;
import com.centralesupelec.chowchow.lib.ShowIsAlreadyLikedException;
import com.centralesupelec.chowchow.lib.ShowIsNotLikedException;
import com.centralesupelec.chowchow.lib.UserNotFoundException;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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

  public void likeShow(LikeDTO likeDTO, Integer userId)
      throws UserNotFoundException, ShowIsAlreadyLikedException {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      throw new UserNotFoundException();
    }
    UserEntity user = maybeUser.get();
    user.likeShow(likeDTO.getMark(), likeDTO.getTmdbShowDTO().getId());
    this.usersService.saveUser(user);
  }

  public void updateMark(LikeDTO likeDTO, Integer userId)
      throws UserNotFoundException, ShowIsNotLikedException {
    Optional<UserEntity> maybeUser = this.usersService.getUserById(userId);
    if (!maybeUser.isPresent()) {
      throw new UserNotFoundException();
    }
    UserEntity user = maybeUser.get();
    user.updateMark(likeDTO.getMark(), likeDTO.getTmdbShowDTO().getId());
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
