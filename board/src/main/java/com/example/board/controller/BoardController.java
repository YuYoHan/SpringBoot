package com.example.board.controller;

import com.example.board.domain.BoardDTO;
import com.example.board.domain.Criteria;
import com.example.board.domain.PageDTO;
import com.example.board.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
@AllArgsConstructor
@Log4j2
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
    public void get(long boardNum, @ModelAttribute("cri") Criteria cri, Model model) {
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

    @GetMapping({"/get","/modify"})
    public void get2(@RequestParam("boardNum") long boardNum, Model model) {
        log.info("/get or modify");
        model.addAttribute("board", service.get(boardNum));
    }

    @PostMapping("/modify")
    public String modify(BoardDTO board, Criteria cri, RedirectAttributes ra) {
        if(service.modify(board)) {
            ra.addFlashAttribute("mn",board.getBoardNum());
        }
        return "redirect:/board/get"+cri.getListLink()+"&boardnum="+board.getBoardNum();
    }

    @PostMapping("/remove")
    public String remove(Long boardnum,Criteria cri) {
        if(service.remove(boardnum)) {
            //
        }
        return "redirect:/board/list"+cri.getListLink();
    }
}
