package com.example.board.controller;

import com.example.board.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reply/*")
@RestController
@AllArgsConstructor
@NoArgsConstructor
public class ReplyController {

    private ReplyService service;


}
