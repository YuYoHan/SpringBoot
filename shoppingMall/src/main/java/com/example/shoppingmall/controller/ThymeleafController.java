package com.example.shoppingmall.controller;

import lombok.extern.log4j.Log4j2;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ThymeleafController {

    @GetMapping("/ex01")
    public String ex08() {
        return "/ex01";
    }

}
