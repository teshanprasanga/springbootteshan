/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.restcontroller;

import com.zetcode.exception.ErrorResponse;
import com.zetcode.exception.UserException;
import com.zetcode.model.User;
import com.zetcode.service.UserService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author teshan.weerapperuma
 */
@RestController
@RequestMapping(path = "/api/users")
public class UserRestController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> list() {
        List<User> list = userService.getAllUsers();

        if (list.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /*Retreiving User details for specified user id*/

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id) throws Exception {
        User user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity(new Error("User with id" + id + " not found"), HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*Update User information with ReuqestBody. JSON*/
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> update(@PathVariable("id") Long id, @RequestBody User user) throws Exception {
        User updatedUser = null;
        User currentUser = null;
        System.out.println("put called");
        try {
            currentUser = userService.getUserById(id);
            if (currentUser == null) {
                return new ResponseEntity(new Error("Unable to update.User with id " + id + " not found."), HttpStatus.NOT_FOUND);
            }

            currentUser.setUserName(user.getUserName());
            if (user.getPassword() != null && (!(user.getPassword().equals("")))) {
                currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            if (user.getContactNo() != null && (!(user.getContactNo().equals("")))) {
                currentUser.setContactNo(user.getContactNo());
            }

            System.out.println("put called");
        } catch (Exception e) {
            System.out.println("" + e.toString());
        }
        updatedUser = userService.updateUser(currentUser);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    /*Insert a new User with RequestBody*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> post(@RequestBody User u, UriComponentsBuilder ucBuilder) throws Exception {
        System.out.println("call for insert user, User name" + u.getUserName());
        if (u.getUserName() == null) {
            return new ResponseEntity("Username is not provided.", HttpStatus.CONFLICT);
        }
        Optional<User> userAlreadyExist = userService.getUserByUserName(u.getUserName());
        if (userAlreadyExist.isPresent()) {
            return new ResponseEntity<>(new Exception("Unable to create User. A User with name " + u.getUserName() + " already exist."), HttpStatus.CONFLICT);
        }

        User newUser = userService.saveUser(u);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/users/{id}").buildAndExpand(u.getId()).toUri());
        return new ResponseEntity<>(newUser, headers, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)

    /*delete a user specifying user Id*/
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) throws Exception {
        System.out.println("called delete method" + id);
        User user = userService.getUserById(id);
        System.out.println("called delete methodw");
        if (user == null) {
            return new ResponseEntity(new Error("Unable to delete. A user with " + id + " does not exist"), HttpStatus.NOT_FOUND);

        }
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @RequestMapping(Model model, value = "/myprofile", method = RequestMethod.GET)
//    public ResponseEntity<?> getLoggedUserDetails() throws Exception {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return new ResponseEntity<>(userService.getUserByUserName(auth.getName()), HttpStatus.OK);
//
//    }
    /*Exception handler*/
    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }

}
