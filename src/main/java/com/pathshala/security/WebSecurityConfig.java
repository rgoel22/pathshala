/*
package com.pathshala.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

*/
/*@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, proxyTargetClass = true)*//*

public class WebSecurityConfig extends WebSecurityConfiguration {

  @Value("${pam.dev.username}")
  private String devUsername;

  @Value("${pam.dev.password}")
  private String devPassword;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  // @formatter:off
  */
/*@Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable()*//*
*/
/*
      .csrf().disable()
      .cors().disable()
      .httpBasic()
        .and()
      .authorizeRequests()
      .anyRequest()
        .permitAll()*//*
*/
/*
    ;
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/logout", "/login");
  }*//*


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception  {
    auth
      .inMemoryAuthentication()
        .withUser(devUsername)
          .password(devPassword)
          .roles("DEVELOPER")
    ;
  }
  //@formatter:on

}
*/
