package com.example.study01.service;

import com.example.study01.domain.UserDTO;
import com.example.study01.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserMapper mapper;

    @Override
    public boolean join(UserDTO user) {
        return mapper.join(user) == 1;
    }

    @Override
    public UserDTO login(String userId, String userPw) {
        UserDTO user = mapper.login(userId, userPw);
        return  user;
    }
}
