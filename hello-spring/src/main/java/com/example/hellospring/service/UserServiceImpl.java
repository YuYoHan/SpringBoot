package com.example.hellospring.service;

import com.example.hellospring.domain.UserDTO;
import com.example.hellospring.mapper.UserMapper;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Setter(onMethod_ = @Autowired)
    private UserMapper mapper;

    @Override
    public boolean join(UserDTO user) {
        return mapper.join(user) == 1;
    }

    @Override
    public UserDTO login(String userId, String userPw) {
        UserDTO user = mapper.login(userId, userPw);
        return user;
    }
}
