package com.centralesupelec.chowchow.TMDB.web;

import com.centralesupelec.chowchow.TMDB.controllers.SearchController;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBSearchDTO;
import com.centralesupelec.chowchow.TMDB.controllers.TMDBShowDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
@RequestMapping(path = "/search")
public class SearchWebController {

  private final SearchController searchController;

  @Autowired
  public SearchWebController(SearchController searchController) {
    this.searchController = searchController;
  }

  @ApiOperation(
      value = "Find a show using the given name",
      notes = "TMDB Api is used to get suggestions regarding the given name.")
  @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity findShowsByName(@RequestParam(value = "name") String name) {
    try {
      TMDBSearchDTO search = this.searchController.findShowsByName(name);
      return ResponseEntity.status(HttpStatus.OK).body(search);
    } catch (HttpStatusCodeException e) {
      return ResponseEntity.status(e.getRawStatusCode()).body(e.getMessage());
    }
  }

  @ApiOperation(
      value = "Retrieve a show with a given id",
      notes = "TMDB api is used to fetch all the information about the show with the given id.")
  @RequestMapping(
      path = "/{id}",
      method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity findShowById(@PathVariable Integer id) {
    try {
      TMDBShowDTO search = this.searchController.findShowById(id);
      return ResponseEntity.status(HttpStatus.OK).body(search);
    } catch (HttpStatusCodeException e) {
      return ResponseEntity.status(e.getRawStatusCode()).body(e.getMessage());
    }
  }
}
