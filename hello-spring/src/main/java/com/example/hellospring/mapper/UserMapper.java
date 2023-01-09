package com.example.hellospring.mapper;

import com.example.hellospring.domain.UserDTO;

public interface UserMapper {
    int join(UserDTO user);

    UserDTO login(String userId, String userPw);
}
