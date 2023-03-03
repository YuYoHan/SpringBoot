package com.example.security_jwt.controller;

import com.example.security_jwt.model.User;
import com.example.security_jwt.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @PostMapping("/join")
    public @ResponseBody String join(User user) {
        log.info(user);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return "join";
    }


}
