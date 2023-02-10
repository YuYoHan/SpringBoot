package com.example.study02.service;

import com.example.study02.VO.BoardVO;
import com.example.study02.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {
    @Autowired
    public BoardMapper mapper;

    public Map<String, Object> selectBoardList(BoardVO boardVO) {
        List<BoardVO> boardVOList = mapper.selectBoardList(boardVO);
        Map<String, Object> boardMap = new HashMap<>();
        boardMap.put("boardList", boardVOList);
        return boardMap;
    }
}
