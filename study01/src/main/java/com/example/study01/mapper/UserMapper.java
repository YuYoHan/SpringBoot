package com.example.study01.mapper;

import com.example.study01.domain.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int join(UserDTO user);

    UserDTO login(@Param("userId") String userId, @Param("userPw") String userPw);
}
