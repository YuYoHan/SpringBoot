package com.example.study01.controller;

import com.example.study01.dto.UserDTO;
import com.example.study01.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
public class UserController {

    // 생성자 주입
    private final UserService userService;

    // 회원가입 페이지 출력 요청
    @GetMapping("/user/save")
    public String saveForm() {return "save";}

    @PostMapping("/user/save")
    public String save(@ModelAttribute("loginUser") UserDTO user) {

        log.info("userId : " + user.getUserId());
        log.info("userEmail : " + user.getUserEmail());
        log.info("userPw : " + user.getUserId());
        log.info("userId : " + user.getUserId());
        userService.save(user);
        return "home";
    }
}
