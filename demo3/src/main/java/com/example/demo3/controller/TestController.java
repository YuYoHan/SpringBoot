package com.example.demo3.controller;

import com.example.demo3.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @RequestMapping("/auth/login")
    public String login(@RequestParam String userId) {
        if(userId.equals("zxzz45")){
            Authentication authentication = new UsernamePasswordAuthenticationToken(userId, null, null);
            String token = JwtProvider.generateToken(authentication);
            return token;
        }
        return "error";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }
}
