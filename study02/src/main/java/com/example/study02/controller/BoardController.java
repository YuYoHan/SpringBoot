package com.example.study02.controller;

import com.example.study02.VO.BoardVO;
import com.example.study02.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    private BoardService service;

    // 게시판 리스트 페이지
    @RequestMapping(value = "/board/boardList", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView boardList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/board/boardList");
        return mv;
    }

    // 게시판 목록 조회
    @RequestMapping(value = "/board/selectBoardList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public ModelAndView selectBoardList(BoardVO paramVO) {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> boardMap = new HashMap<String, Object>();
        boardMap = service.selectBoardList(paramVO);
        mv.addObject("boardMap", boardMap);
        mv.setViewName("jsonView");
        return mv;
    }
}
