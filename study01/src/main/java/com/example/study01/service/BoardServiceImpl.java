package com.example.study01.service;

import com.example.study01.domain.BoardDTO;
import com.example.study01.mapper.BoardMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

    private BoardMapper mapper;

    @Override
    public void register(BoardDTO board) {

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

    @Override
    public List<BoardDTO> getList() {
        return null;
    }
}
