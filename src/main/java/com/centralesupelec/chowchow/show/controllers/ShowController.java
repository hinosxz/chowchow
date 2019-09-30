package com.centralesupelec.chowchow.show.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ShowController {

    private ShowService showService;

    @Autowired
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    public ShowDTO getShowById(Long id) {
        return this.showService.getShowById(id);
    }
}
