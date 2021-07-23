/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.security;

import com.zetcode.model.User;
import com.zetcode.repository.UserRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author teshan.weerapperuma
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
//        if (user == null) {
//            throw new UsernameNotFoundException("UserName " + userName + " not found");
//        }
        user.orElseThrow(() -> new UsernameNotFoundException("UserName " + userName + " not found"));
        return user.map(MyUserDetails::new).get();
    }

}


