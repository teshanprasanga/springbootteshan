/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.auth.service;


import com.zetcode.model.User;

import com.zetcode.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author teshan.weerapperuma
 */
@Service
@Transactional
public class SignUpService {
    
    @Autowired
    UserRepository userRepository;
   
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    
    @PostConstruct
    private void setupDefaultUser() {
        //-- just to make sure there is an ADMIN user exist in the database for testing purpose
        if (userRepository.count() == 0) {
            System.out.println("creating default user");
            User u=new User();
            u.setUserName("user");
            u.setPassword( passwordEncoder.encode("pass"));
           
            
          
            userRepository.save(u);
            
//           userRepository.save(new User("user",
//                    passwordEncoder.encode("pass"),
//                    Stream.of(new Role("USER"), new Role("ADMIN"))
//                            .collect(Collectors.toList())));
            

        }
}
}