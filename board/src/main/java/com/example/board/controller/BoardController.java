package com.example.board.controller;

import com.example.board.domain.BoardDTO;
import com.example.board.domain.Criteria;
import com.example.board.domain.PageDTO;
import com.example.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {

    private BoardService service;

    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        model.addAttribute("list", service.getList(cri));
        model.addAttribute("pageMaker",new PageDTO(service.count(cri), cri));
    }

    @GetMapping("/regist")
    public void regist() {}

    @GetMapping("/get")
    public void get(long boardNum, Model model) {
        model.addAttribute("board", service.get(boardNum));
    }


    @PostMapping("/regist")
    // Model model도 request로 넘겨주는 것이라
    // redirect면 다 날라간다.
    // 이럴때 RedirectAttributes 매개변수를 사용한다.
    public String regist(BoardDTO board, RedirectAttributes ra) {
        service.regist(board);
        int boardNum = service.getMaxBoardNum(board.getUserId());
        ra.addFlashAttribute("boardNum", boardNum);
        return "redirect:/board/list";
    }

}
