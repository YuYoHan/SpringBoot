package com.example.board.service;

import com.example.board.domain.UserDTO;

public interface UserService {
    boolean singUp(UserDTO user);

    UserDTO login(String userId, String userPw);
}
