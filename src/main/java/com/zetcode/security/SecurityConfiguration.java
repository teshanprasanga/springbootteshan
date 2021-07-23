/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 *
 * @author teshan.weerapperuma
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    /*@Override  
    public void configure(HttpSecurity http) throws Exception {  
        http  
            .authorizeRequests()  
            .anyRequest().authenticated()  
            .and()  
            .httpBasic();  
    }  */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //   auth.inMemoryAuthentication()
        //.withUser("user")
        //  .password("{noop}pass") // Spring Security 5 requires specifying the password storage format  
        //  .roles("USER");
        auth.userDetailsService(myUserDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/rrr/**").permitAll()
                .and()
                .formLogin()
                .and()
                .logout().logoutUrl("/logout").and().cors().and().csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
