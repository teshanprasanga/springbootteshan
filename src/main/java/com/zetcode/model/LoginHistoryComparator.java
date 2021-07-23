/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.model;

import java.util.Comparator;

/**
 *
 * @author teshan.weerapperuma
 */
public class LoginHistoryComparator implements Comparator<LoginHistory>{

    @Override
    public int compare(LoginHistory o1, LoginHistory o2) {
       return o1.getLoginTime().compareTo(o2.getLoginTime());
    }
    
    
    
}
