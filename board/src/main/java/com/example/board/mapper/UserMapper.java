package com.example.board.mapper;

import com.example.board.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int signUp(UserDTO user);

    UserDTO login(@Param("userId") String userId, @Param("userPw") String userPw);
}
