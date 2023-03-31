package com.example.board2.service;

import com.example.board2.domain.UserDTO;

import java.util.List;

public interface UserSerivce {
    boolean join(UserDTO userDTO);
    UserDTO login(String userId, String userPw);

    List<UserDTO> getAllUser();

    UserDTO getUser(Long id);

    UserDTO update(UserDTO userDTO);

    void delete(Long id);
}
