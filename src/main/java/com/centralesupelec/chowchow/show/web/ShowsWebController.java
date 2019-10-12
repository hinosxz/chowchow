package com.centralesupelec.chowchow.show.web;

import com.centralesupelec.chowchow.show.controllers.ShowDTO;
import com.centralesupelec.chowchow.show.controllers.ShowsController;
import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.domain.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@Controller
@RequestMapping(path = "/shows")
public class ShowsWebController {

    private final ShowsController showsController;

    @Autowired
    public ShowsWebController(ShowsController showsController) {
        this.showsController = showsController;
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getShowById(
            @PathVariable(value = "id") Long id,
            @RequestParam("isUserPremium") boolean isUserPremium // TODO: remove this when session is implemented
    ) {
        // TODO: implemente user sessions
        UserDTO userDTO = this.getMockUserDTO(isUserPremium);
        Optional<ShowDTO> maybeShowDTO = this.showsController.getShowById(id, userDTO);
        return maybeShowDTO.map(showDTO ->
                    new ResponseEntity<>(showDTO, HttpStatus.OK)
                )
                .orElseGet(() ->
                    new ResponseEntity<>(HttpStatus.NOT_FOUND)
                );
    }

    private UserDTO getMockUserDTO(boolean isUserPremium) {
        return new UserDTO(1L, "test", isUserPremium ? SubscriptionType.GOLD : null);
    }
}
