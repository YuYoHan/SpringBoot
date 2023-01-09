package com.example.hellospring.service;

import com.example.hellospring.domain.UserDTO;

public interface UserService {
    boolean join(UserDTO user);

    UserDTO login(String userId, String userPw);
}
