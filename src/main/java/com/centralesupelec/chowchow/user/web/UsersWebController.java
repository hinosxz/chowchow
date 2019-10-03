package com.centralesupelec.chowchow.user.web;
import java.util.concurrent.ExecutionException;

import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/users")
public class UsersWebController {
    private final UsersController usersController;

    @Autowired
    public UsersWebController(UsersController usersController){
        this.usersController = usersController;
    }

    @RequestMapping(path="", method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody UserDTO userDTO){
        try {
            boolean result = this.usersController
                .createUser(userDTO.getUserName(), userDTO.getPassword());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (InterruptedException | ExecutionException executionException) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
