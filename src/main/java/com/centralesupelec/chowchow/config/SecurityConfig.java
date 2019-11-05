package com.centralesupelec.chowchow.config;

import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UsersService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UsersService usersService;

  @Autowired
  public SecurityConfig(UsersService usersService) {
    this.usersService = usersService;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(this.usersService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {})
        .and()
        .authenticationProvider(this.getAppAuthProvider())
        .formLogin()
        .loginProcessingUrl("/login")
        .successHandler(new AuthenticationLoginSuccessHandler())
        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessHandler(new AuthenticationLogoutSuccessHandler())
        .invalidateHttpSession(true)
        .and()
        .authorizeRequests()
        .antMatchers("/login")
        .permitAll()
        .antMatchers("/logout")
        .permitAll()
        .antMatchers("/register")
        .permitAll()
        .antMatchers("/**")
        .authenticated()
        .anyRequest()
        .permitAll();
  }

  private static class AuthenticationLoginSuccessHandler
      extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
      UserEntity user = (UserEntity) authentication.getPrincipal();
      request.getSession().setAttribute("USER_ID", user.getId());
      response.setStatus(HttpServletResponse.SC_OK);
    }
  }

  private static class AuthenticationLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Override
    public void onLogoutSuccess(
        HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
      request.getSession().setAttribute("USER_ID", null);
      response.setStatus(HttpServletResponse.SC_OK);
    }
  }

  public AppAuthProvider getAppAuthProvider() {
    AppAuthProvider appAuthProvider = new AppAuthProvider();
    appAuthProvider.setUserDetailsService(this.usersService);
    return appAuthProvider;
  }
}
