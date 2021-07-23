/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.service;

import com.zetcode.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author teshan.weerapperuma
 */
public interface  UserService {
    public List<User> getAllUsers();
    public User getUserById(long userId);
    public User saveUser(User u);
    public User updateUser(User u);
    public void deleteUser(long id);
    public Optional<User> getUserByUserName(String userName);
    
    
}
