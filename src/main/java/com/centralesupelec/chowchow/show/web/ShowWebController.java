package com.centralesupelec.chowchow.show.web;

import com.centralesupelec.chowchow.show.controllers.ShowController;
import com.centralesupelec.chowchow.show.controllers.ShowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path = "/shows")
public class ShowWebController {

    private final ShowController showController;

    @Autowired
    public ShowWebController(ShowController showController) {
        this.showController = showController;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShowDTO> getShowById(@PathVariable(value = "id") String id) {
        return new ResponseEntity<>(this.showController.getShowById(Long.parseLong(id)), HttpStatus.OK);
    }
}
