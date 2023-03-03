package com.example.web_sty.controller;

import com.example.web_sty.dto.BoardDTO;
import com.example.web_sty.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;


    @GetMapping("/save")
    public String saveForm() {
        return "/board/save";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        log.info("boardDTO : " + boardDTO);
        boardService.save(boardDTO);
        return "/board/save";
    }
}
