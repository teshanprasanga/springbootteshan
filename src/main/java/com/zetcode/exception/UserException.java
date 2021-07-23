/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zetcode.exception;

/**
 *
 * @author teshan.weerapperuma
 */
public class UserException extends Exception {

   private static final long serialVersionUID = 1L;
    private String errorMessage;

    public UserException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;

    }

    public UserException() {
        super();
    }
}
