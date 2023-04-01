package com.example.board2.service;

import com.example.board2.domain.UserDTO;
import com.example.board2.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements  UserSerivce {

    private UserMapper mapper;

    @Override
    public boolean join(UserDTO userDTO) {
        return mapper.join(userDTO) ==1;
    }

    @Override
    public UserDTO login(String userId, String userPw) {
        return null;
    }

    @Override
    public List<UserDTO> getAllUser() {
        return null;
    }

    @Override
    public UserDTO getUser(Long id) {
        return null;
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
