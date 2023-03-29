package com.example.test.controller;

import com.example.test.DTO.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {
    @GetMapping("/api/test")
    public String test() {
        return "rest api result return!";
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        log.info("User : " + user);
        return user;
    }

}
