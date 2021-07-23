/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author teshan.weerapperuma
 */
@Controller
public class WebController {

   
    
     @GetMapping("/")
    public String home1() {
        return "index";
    }

   

    // Login form  
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error  
    @GetMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
     @GetMapping("/appusers")
    public String usersPage() {
        return "appusers";
    }
    
    
}
