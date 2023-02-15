package com.example.web_sty.controller;

import com.example.web_sty.dto.UserDTO;
import com.example.web_sty.service.userService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;

@Controller
@Log4j2
@RequiredArgsConstructor
public class UserController {

    // 생성자 주입
    private final userService userService;


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
    public String save(@ModelAttribute UserDTO userDTO) {
        log.info("userDTO = " + userDTO);
        userService.save(userDTO);
        return "/user/login";
    }

    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO userDTO) {
        UserDTO loginResult = userService.login(userDTO);
        if(loginResult != null) {
            // login 성공
            return "/";
        } else {
            // login 실패
            return "/user/login";
        }
    }



}
