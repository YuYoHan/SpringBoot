package com.example.board.controller;

import com.example.board.domain.UserDTO;
import com.example.board.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@Log4j2
public class UserController {

    private UserService service;

    @GetMapping("/user/signUp")
    public String signUp() {
        return "/user/signUp";
    }

    @GetMapping("/user/login")
    public String login() {
        return "/user/login";
    }

    @PostMapping("/user/signUp")
    public String signUp(UserDTO user, HttpServletResponse resp) {
        if (service.singUp(user)) {
            Cookie cookie = new Cookie("userId", user.getUserId());
            cookie.setMaxAge(300);
            resp.addCookie(cookie);
        }
        return "redirect:/";
    }

    @PostMapping("/user/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        UserDTO user = service.login(userId, userPw);
        if(user != null) {
            session.setAttribute("userId", user.getUserId());
            model.addAttribute("loginUser", session.getAttribute("userId"));
        }
        return "home";
    }

    @GetMapping("/user/logout")
    public String logout(HttpServletRequest req) {
        req.getSession().invalidate();
        return "home";
    }
}
