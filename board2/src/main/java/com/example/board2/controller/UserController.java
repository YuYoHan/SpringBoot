package com.example.board2.controller;

import com.example.board2.domain.UserDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/")
    public UserDTO userData(@RequestBody UserDTO userDTO) {
        return userDTO;
    }

}
