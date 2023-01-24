package com.example.board.service;

import com.example.board.domain.BoardDTO;
import com.example.board.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
    private BoardMapper mapper;

    @Override
    public List<BoardDTO> getList() {
        log.info(mapper.getList());
        return mapper.getList();
    }

    @Override
    public void regist(BoardDTO board) {

    }

    @Override
    public BoardDTO get(long boardNum) {
        return null;
    }

    @Override
    public boolean modify(BoardDTO board) {
        return false;
    }

    @Override
    public boolean remove(long boardNum) {
        return false;
    }
}