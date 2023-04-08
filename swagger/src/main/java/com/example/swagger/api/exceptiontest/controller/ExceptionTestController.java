package com.example.swagger.api.exceptiontest.controller;

import com.example.swagger.api.exceptiontest.dto.BindExceptionTestDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exception")
public class ExceptionTestController {

    @GetMapping("/bind-exception-test")
    public String bindExceptionTest(@Valid BindExceptionTestDTO bindExceptionTestDTO) {
        return "ok";
    }




}
