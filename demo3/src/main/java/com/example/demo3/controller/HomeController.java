package com.example.demo3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {


    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }



    @GetMapping("/hello")
    public String hello() {
        return "/hello";
    }
}
