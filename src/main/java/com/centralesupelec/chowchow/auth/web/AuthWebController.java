package com.centralesupelec.chowchow.auth.web;

import com.centralesupelec.chowchow.auth.controllers.AuthController;
import com.centralesupelec.chowchow.auth.controllers.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthWebController {

  private final AuthController authController;

  @Autowired
  public AuthWebController(AuthController authController) {
    this.authController = authController;
  }

  @RequestMapping(path = "sign_up", method = RequestMethod.POST)
  public boolean signUp(@RequestBody CreateUserDTO createUserDTO) {
    return this.authController.signUp(createUserDTO);
  }
}
