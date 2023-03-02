package com.example.security_jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
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

    @GetMapping("/join")
    public String join() {
        return "join";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/joinProc")
    public String joinProc() {
        return "login";
    }

}
