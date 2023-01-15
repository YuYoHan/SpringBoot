package com.example.study01.controller;

import com.example.study01.domain.BoardDTO;
import com.example.study01.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequestMapping("/board/*")
// 생성자 주입할 때 사용하는 어노테이션
@AllArgsConstructor
public class BoardController {
    private BoardService service;


    @GetMapping("/list")
    public void list(Model model) {
        log.info("list");
        model.addAttribute("list", service.getList());
    }

    @PostMapping("/register")
    // Model model도 request로 넘겨주는 것이라
    // redirect면 다 날라간다.
    // 이럴때 RedirectAttributes 매개변수를 사용한다.
    // RedirectAttributes를 사용하는 이유는 등록 작업이 끝난 후 다시 목록 화면으로
    // 이동하기 위함이다. 추가적으로 새롭게 등록된 게시물의 번호를 같이 전달하기 위해서 이용한다.
    public String register(BoardDTO board, RedirectAttributes rttr) {
        log.info("register : " + board);
        service.regist(board);
        // 데이터가 post 방식으로 전달된다.
        // 데이터가 한번만 사용된다.
        rttr.addFlashAttribute("result : " + board.getBoardNum());

        return "redirect:/board/list";
    }

    @GetMapping("/get")
    public void get(@RequestParam("boardNum") long boardNum, Model model) {
        model.addAttribute("board", service.get(boardNum));
    }

    @PostMapping("/modify")
    public String modify(BoardDTO board, RedirectAttributes rttr) {
        log.info("modify : " + board);

        if(service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }
    @PostMapping("/remove")
    public String remove(@RequestParam("boardNum") long boardNum, RedirectAttributes rttr) {
        log.info("remove...." + boardNum);

        if(service.remove(boardNum)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list";
    }


}
