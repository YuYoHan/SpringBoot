package com.example.backend_rest.controller;

import com.example.backend_rest.dto.ResponseDTO;
import com.example.backend_rest.dto.UserDTO;
import com.example.backend_rest.model.UserEntity;
import com.example.backend_rest.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try{
            if(userDTO == null || userDTO.getPassword() == null) {
                throw new RuntimeException("Invalid Password value");
            }
            // 요청을 이용해 저장할 유저 만들기
            UserEntity user = UserEntity.builder()
                    .username(userDTO.getUserName())
                    .password(userDTO.getPassword())
                    .build();
            // 서비스를 이용해 레포지토리에 유저 저장
            UserEntity registeredUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .id(registeredUser.getId())
                    .userName(registeredUser.getUsername())
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> authenicate(@RequestBody UserDTO userDTO) {
        UserEntity user = userService.getByCredentials(
                userDTO.getUserName(),
                userDTO.getPassword()
        );

        if(user != null) {
            final UserDTO responseUserDTO = UserDTO.builder()
                    .userName(user.getUsername())
                    .id(user.getId())
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } else  {
            ResponseDTO responseDTO = ResponseDTO.builder()
                    .error("Login failed")
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
