package com.example.study01.service;

import com.example.study01.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    void register(BoardDTO board);
    BoardDTO get(long boardNum);
    boolean modify(BoardDTO board);
    boolean remove(long boardNum);
    List<BoardDTO> getList();
}
