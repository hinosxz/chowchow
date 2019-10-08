package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UsersController {
    private final UsersServiceImpl usersServiceImpl;

    @Autowired
    public UsersController(UsersServiceImpl usersServiceImpl) {
       this.usersServiceImpl = usersServiceImpl;
    }

    public boolean createUser(String username, String password) {
        Optional<UserEntity> maybeUser = this.usersServiceImpl.getUserByUsername(username);
        if (!maybeUser.isPresent()){
            UserEntity newUser = new UserEntity();
            newUser.setUsername(username);
            newUser.setPassword(password);
            usersServiceImpl.saveUser(newUser);
            return true;
        } else {
            return false;
        }
    }

}
