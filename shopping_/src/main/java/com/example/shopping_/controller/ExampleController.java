package com.example.shopping_.controller;

import com.example.shopping_.DTO.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping(value = "/test")
    public UserDTO test() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAge(20);
        userDTO.setName("minju");

        return userDTO;
    }
}
