package com.example.web_sty.service;

import com.example.web_sty.dto.userDTO;
import com.example.web_sty.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class userService {

    private final UserRepository userRepository;
    public void save(userDTO userDTO) {
    }
}
