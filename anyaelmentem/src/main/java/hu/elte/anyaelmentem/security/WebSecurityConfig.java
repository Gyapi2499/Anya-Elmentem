/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.anyaelmentem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author stefa
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Value("${need.test}")
    private boolean test;

    protected void configure(HttpSecurity http) throws Exception {
     if(!test){
      http
          .cors()
              .and()
          .csrf().disable()
          .authorizeRequests()
              .antMatchers("/h2/**", "/users/users/register").permitAll()   // important!
              .anyRequest().authenticated()
              .and()
          .httpBasic()
              .authenticationEntryPoint(getBasicAuthEntryPoint())
              .and()
          .headers()      // important!
              .frameOptions().disable()
              .and()
          .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       }else{
         http
          .cors()
              .and()
          .csrf().disable()
          .authorizeRequests()
              .antMatchers("/**").permitAll()   // important!
              .anyRequest().authenticated()
              .and()
          .httpBasic()
              .authenticationEntryPoint(getBasicAuthEntryPoint())
              .and()
          .headers()      // important!
              .frameOptions().disable()
              .and()
          .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
    }    
    @Autowired
    protected void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
       if(!test){
        auth
          .userDetailsService(userDetailsService)
          .passwordEncoder(passwordEncoder());
        }else{
        auth
          .inMemoryAuthentication()
          .withUser("anya@anya.com").password("$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..").roles("ADMIN");
    }
    }
    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    if(!test){
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }else{
        auth
          .inMemoryAuthentication()
          .withUser("anya@anya.com").password("$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..").roles("ADMIN");
    }
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
        return new CustomBasicAuthenticationEntryPoint();
    }
}

