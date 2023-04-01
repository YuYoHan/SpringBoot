package com.example.board2.mapper;

import com.example.board2.domain.UserDTO;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int join(UserDTO userDTO);
    UserDTO login(@Param("userId") String userId, @Param)
}
