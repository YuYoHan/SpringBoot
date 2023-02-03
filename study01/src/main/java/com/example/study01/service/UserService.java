package com.example.study01.service;


import com.example.study01.dto.UserDTO;
import com.example.study01.repository.UserReposiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReposiory userReposiory;

    public void save(UserDTO user) {
    }
}
