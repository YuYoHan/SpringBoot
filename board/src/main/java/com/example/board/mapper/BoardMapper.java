package com.example.board.mapper;

import com.example.board.domain.BoardDTO;
import com.example.board.domain.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> getList(Criteria cri);
    void insert(BoardDTO board);
    BoardDTO getDetail(long boardNum);
    int getMaxBoardNum(String userId);

    int getTotal(Criteria cri);
}
