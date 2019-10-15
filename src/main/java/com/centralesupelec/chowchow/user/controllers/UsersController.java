package com.centralesupelec.chowchow.user.controllers;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class UsersController {

    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
       this.usersService = usersService;
    }

    public boolean createUser(UserSignUpDTO userSignUpDTO) {
        Optional<UserEntity> maybeUser = this.usersService.getUserByUsername(userSignUpDTO.getUsername());
        if (maybeUser.isPresent()){
            return false;
        }
        usersService.saveUser(UserSignUpDTO.toEntity(userSignUpDTO));
        return true;
    }
}
