package com.centralesupelec.chowchow.lib;

import com.centralesupelec.chowchow.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AppAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    public AppAuthenticationProvider(UsersService usersService) {
        this.setUserDetailsService(usersService);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String username = auth.getName();
        String password = auth.getCredentials().toString();
        System.out.println(username);
        System.out.println(password);
        UserDetails user = this.getUserDetailsService().loadUserByUsername(username);
        if (Objects.isNull(user) || !Objects.equals(password, user.getPassword())) {
            throw new BadCredentialsException(
                    String.format("Username / Password does not match for %s", auth.getPrincipal())
            );
        }
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

    public UserDetailsService getUserDetailsService() {
        return super.getUserDetailsService();
    }
}
