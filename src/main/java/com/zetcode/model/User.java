/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.model;

/**
 *
 * @author teshan.weerapperuma
 */
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;

@Entity
@Table(name = "tbl_user")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "user_id")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(unique = true)
    private String userName;
    private boolean active;
    private String contactNo;

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    

    public Long getId() {
        return id;
    }

    public User() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
   
    public User(String userName, String password,String contactNo) {
        this.userName = userName;
        this.password = password;
        this.contactNo=contactNo;
      
    }
    
   /* @OneToMany(mappedBy = "user")
    List<LoginHistory> loginHistoryArrayList=new ArrayList<>();
    
    private List<LoginHistory> getLoginHistoryArray(){
      return loginHistoryArrayList;
    }
    
    public String getLastLogin(){
    
        Collections.sort(this.loginHistoryArrayList,new LoginHistoryComparator());
        LoginHistory loginHistory=(loginHistoryArrayList.size()>0)?loginHistoryArrayList.get(loginHistoryArrayList.size()-1):null;
        return (loginHistory!=null)?loginHistory.getLoginTime().toString()+"from IP"+loginHistory.getLoginIp():"";
    }
    
    */
    
}
