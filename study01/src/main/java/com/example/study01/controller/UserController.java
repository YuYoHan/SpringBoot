package com.example.study01.controller;

import com.example.study01.domain.UserDTO;
import com.example.study01.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService service;

    @GetMapping("/members/signUp")
    public String join() {
        return "/members/signUp";
    }

    @GetMapping("/members/login")
    public String login() {
        return "/members/login";
    }

    @PostMapping("/members/signUp")
    public String join(UserDTO user, HttpServletResponse resp) {
        if (service.join(user) ) {
            Cookie cookie = new Cookie("userId", user.getUserId());
            cookie.setMaxAge(300);
            resp.addCookie(cookie);
        }
        return "redirect:/";
    }

    @PostMapping("/members/login")
    public String login(@CookieValue(name="userId", required = false) String userId, String userPw, HttpServletRequest req, Model model) {
        HttpSession session = req.getSession();
        UserDTO user = service.login(userId, userPw);
        if(user != null) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userPw", user.getUserPw());
//            model.addAttribute("userId", session.getAttribute("userId"));
//            model.addAttribute("userPw", session.getAttribute("userPw"));
            Cookie[] cookies = req.getCookies();

            for(Cookie c : cookies) {
                model.addAttribute("cookie_name", c.getName());
                model.addAttribute("cookie_value", c.getValue());
            }

        }
        return "home";
    }
}
