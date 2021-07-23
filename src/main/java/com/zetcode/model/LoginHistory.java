/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author teshan.weerapperuma
 */
@Entity
public class LoginHistory {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long loginHistoryId;
    
    private String loginIp;
    
    private String userAgent;
    
    private LocalDateTime loginTime;
    @ManyToOne
    @JoinColumn(name="user_id",nullable = true)
    private User user;
    
    public LoginHistory(){}

    public LoginHistory(String loginIp, String userAgent, LocalDateTime loginTime) {
        this.loginIp = loginIp;
        this.userAgent = userAgent;
        this.loginTime = loginTime;
    }

    public long getLoginHistoryId() {
        return loginHistoryId;
    }

    public void setLoginHistoryId(long loginHistoryId) {
        this.loginHistoryId = loginHistoryId;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
            
    
    
         
    
}
