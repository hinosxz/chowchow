package com.centralesupelec.chowchow.lib;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptManagerUtil {

    public static PasswordEncoder passwordencoder(){
        return new BCryptPasswordEncoder();
    }
}
