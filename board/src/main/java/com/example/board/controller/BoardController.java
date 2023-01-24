package com.example.board.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {


    @GetMapping("/list")
    public void list() {}

    @GetMapping("/regist")
    public void regist() {}


}
