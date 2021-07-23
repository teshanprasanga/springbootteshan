/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.service;

import com.zetcode.model.User;
import com.zetcode.repository.UserRepository;
import com.zetcode.security.MyUserDetails;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author teshan.weerapperuma
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();

    }

    @Override
    public User getUserById(long userId) {
        return userRepository.getOne(userId);
    }
    
    

    @Override
    public User saveUser(User u) {
        u.setPassword(passwordEncoder.encode(u.getPassword()));
       return userRepository.save(u);
    }

    @Override
    public User updateUser(User u) {
     return   userRepository.save(u);
    }

    @Override
    public void deleteUser(long id) {
       userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getUserByUserName(String userName) throws EntityNotFoundException{
             // Optional<User> user = userRepository.findByUserName(userName);
//        if (user == null) {
//            throw new UsernameNotFoundException("UserName " + userName + " not found");
//        }
       return userRepository.findByUserName(userName);
      
        
    }

}
