package com.example.web_sty.controller;

import com.example.web_sty.dto.UserDTO;
import com.example.web_sty.service.userService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @GetMapping("/user/login")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/user/login")
    // session 사용 : HttpSession
    public String login(@ModelAttribute UserDTO userDTO, HttpSession session) {
        UserDTO loginResult = userService.login(userDTO);
        if(loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getUserEmail());
            log.info("userEmail : " + loginResult.getUserEmail());
            log.info("userPw : " + loginResult.getUserPw());
            return "/home";
        } else {
            // login 실패
            return "/user/login";
        }
    }

    @GetMapping("/user/")
    public String findAll(Model model) {
        List<UserDTO> userDTOList = userService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("userList", userDTOList);
        return "user/list";
    }

    @GetMapping("/user/{id}")
    // 경로상의 값(/user/{id})를 가져올 때는 @PathVariable을 쓴다.
    public String findById(@PathVariable Long id, Model model) {
        UserDTO userDTO = userService.findById(id);
        model.addAttribute("user", userDTO);
        return "detail";
    }


}
