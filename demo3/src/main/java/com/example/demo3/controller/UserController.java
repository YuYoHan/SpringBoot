package com.example.demo3.controller;

import com.example.demo3.DTO.UserDTO;
import com.example.demo3.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Log4j2
@RequiredArgsConstructor
public class UserController {

    // 생성자 주입
    private final UserService userService;


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
    public String save(@ModelAttribute("loginUser") UserDTO user) {

//        log.info("userId : " + user.getUserId());
//        log.info("userEmail : " + user.getUserEmail());
//        log.info("userPw : " + user.getUserId());
//        log.info("userId : " + user.getUserId());
        userService.save(user);
        return "/user/login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute("loginUser") UserDTO user, HttpSession session) {
        UserDTO loginResult = userService.login(user);
        if(loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getUserEmail());
            return "home";
        } else {
            // login 실패
            return "/user/login";
        }
    }
}
