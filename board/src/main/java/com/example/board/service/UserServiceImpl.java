package com.example.board.service;

import com.example.board.domain.UserDTO;
import com.example.board.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserMapper mapper;

    @Override
    public boolean singUp(UserDTO user) {
        return mapper.signUp(user) == 1;
    }

    @Override
    public UserDTO login(String userId, String userPw) {
        UserDTO user =  mapper.login(userId, userPw);
        return user;
    }
}
