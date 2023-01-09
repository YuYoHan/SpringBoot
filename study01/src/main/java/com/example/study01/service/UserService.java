package com.example.study01.service;

import com.example.study01.domain.UserDTO;

public interface UserService {

    boolean join(UserDTO user);

    UserDTO login(String userId, String userPw);
}
