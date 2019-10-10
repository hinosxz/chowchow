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

    public boolean createUser(UserDTO userDTO) {
        Optional<UserEntity> maybeUser = this.usersServiceImpl.getUserByUsername(userDTO.getUsername());
        if (maybeUser.isPresent()){
            return false;
        }
        usersServiceImpl.saveUser(UserDTO.toEntity(userDTO));
        return true;
    }
}
