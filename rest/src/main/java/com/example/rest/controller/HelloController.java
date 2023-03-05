package com.example.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    // GET
    // /hello-world
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }
}
