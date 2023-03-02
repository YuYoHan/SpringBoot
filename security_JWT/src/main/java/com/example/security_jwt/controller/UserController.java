package com.example.security_jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    // 현재 상황은 스프링 시큐리티가 낚아챔!
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc() {
        return "회원가입이 완료됨!";
    }

}
