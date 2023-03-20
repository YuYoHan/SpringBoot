package com.example.rest.user;

// 2xx → OK
// 4xx → Client
// 5xx → Server
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg) {
        super(msg);
    }
}
