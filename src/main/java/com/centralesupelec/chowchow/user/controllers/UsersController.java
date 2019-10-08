package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersServiceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Controller
public class UsersController {
    private final UsersServiceImplementation usersServiceImplementation;

    @Autowired
    public UsersController(UsersServiceImplementation usersServiceImplementation) {
       this.usersServiceImplementation = usersServiceImplementation;
    }

    public boolean createUser(String username, String password)
    {        Optional<UserEntity> maybeUser = this.usersServiceImplementation.getUserByUsername(username);
        if (!maybeUser.isPresent()){
            UserEntity newUser = new UserEntity();
            newUser.setUsername(username);
            newUser.setPassword(password);
            usersServiceImplementation.saveUser(newUser);
            return true;
        }
        else
        {
            return false;
        }

    }

}
