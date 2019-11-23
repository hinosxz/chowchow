package com.centralesupelec.chowchow.config;

import com.centralesupelec.chowchow.user.controllers.AppAuthProvider;
import com.centralesupelec.chowchow.user.domain.UserEntity;
import com.centralesupelec.chowchow.user.service.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserServiceImpl userServiceImpl;
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public SecurityConfig(UserServiceImpl userServiceImpl, PasswordEncoder passwordEncoder) {
    this.userServiceImpl = userServiceImpl;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(this.getAppAuthProvider());
  }

  /**
   * We use this method to override web security. That way we allow swagger-ui, api-docs, errors
   * etc...
   */
  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers("/manage/health");
    web.ignoring().antMatchers("/swagger-ui.html");
    web.ignoring().antMatchers("/webjars/**");
    web.ignoring().antMatchers("/v2/api-docs/**");
    web.ignoring().antMatchers("/error");
    web.ignoring().antMatchers("/swagger-resources/**");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors()
        .and()
        .csrf()
        .disable()
        .exceptionHandling()
        .authenticationEntryPoint(new Http403ForbiddenEntryPoint() {})
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
        .invalidateHttpSession(true);
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
    appAuthProvider.setUserDetailsService(this.userServiceImpl);
    appAuthProvider.setPasswordEncoder(this.passwordEncoder);
    return appAuthProvider;
  }
}
