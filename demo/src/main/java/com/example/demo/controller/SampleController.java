package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SampleController {

    @GetMapping("/user/userInfo")
    public String userInfo() {

        return "/user/userInfo";
    }

    @GetMapping("/user/userData")
    public String userData() {

        return "/user/userinfo";
    }

    @PostMapping("/post")
    public String demoPost() {

        return "/post/post";
    }
}
