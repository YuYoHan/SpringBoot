package com.example.hellospring.mapper;

import com.example.hellospring.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int join(UserDTO user);

    UserDTO login(String userId, String userPw);
}
