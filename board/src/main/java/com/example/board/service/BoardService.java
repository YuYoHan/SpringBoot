package com.example.board.service;

import com.example.board.domain.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> getList();
    void regist(BoardDTO board);
    int getMaxBoardNum(String userId);
    BoardDTO get(long boardNum);
    boolean modify(BoardDTO board);
    public boolean remove(long boardNum);

    int count();
}
