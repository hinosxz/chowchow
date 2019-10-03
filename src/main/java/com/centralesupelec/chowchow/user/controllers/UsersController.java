package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
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

    public boolean createUser(String userName, String password)
            throws InterruptedException, ExecutionException {
        Optional<UserEntity> maybeUser = this.usersServiceImplementation.getUserByUserName(userName).get();
        if (!maybeUser.isPresent()){
            UserEntity newUser = new UserEntity();
            newUser.setUserName(userName);
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
