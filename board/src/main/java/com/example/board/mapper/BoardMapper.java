package com.example.board.mapper;

import com.example.board.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> getList();
    void insert(BoardDTO board);
    BoardDTO getDetail(long boardNum);
    int getMaxBoardNum(String userId);

    int getTotal();
}
