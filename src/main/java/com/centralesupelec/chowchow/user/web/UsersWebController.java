package com.centralesupelec.chowchow.user.web;

import com.centralesupelec.chowchow.user.controllers.UserDTO;
import com.centralesupelec.chowchow.user.controllers.UsersController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
            boolean result = this.usersController
                .createUser(userDTO.getUsername(), userDTO.getPassword());
            return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
