package com.example.study01.mapper;

import com.example.study01.domain.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardDTO> getList();
    void insert(BoardDTO board);
    BoardDTO getDetail(long boardNum);

}
