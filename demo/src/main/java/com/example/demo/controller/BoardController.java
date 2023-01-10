package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {


    @GetMapping("/hello")
    public void hello(Model model) {
        model.addAttribute("greeting", "hello 타임리프~");
    }
}
