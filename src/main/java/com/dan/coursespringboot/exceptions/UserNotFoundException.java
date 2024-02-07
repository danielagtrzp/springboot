package com.dan.coursespringboot.exceptions;

public class UserNotFoundException extends Exception{
    
    public UserNotFoundException(String message) {
        super(message);
    }
}
