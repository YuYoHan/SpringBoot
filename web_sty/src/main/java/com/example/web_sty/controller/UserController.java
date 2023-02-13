package com.example.web_sty.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/login")
    public String login(HttpServletRequest req) {
        return "/user/login";
    }

    // 회원가입 페이지 출력 요청
    @GetMapping("/user/save")
    public String saveForm(Model model) {
        return "/user/save";
    }

    @PostMapping("/user/save")
    public String save(
            @RequestParam("userEmail") String userEmail,
            @RequestParam("userPw") String userPw,
            @RequestParam("userName") String userName) {
        return "home";

    }



}
