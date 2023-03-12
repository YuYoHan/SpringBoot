package com.example.board.mapper;

import com.example.board.domain.Criteria;
import com.example.board.domain.ReplyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReplyMapper {
    int insert(ReplyDTO replyDTO);
    int delete(Long replyNum);
    int update(ReplyDTO replyDTO);

    List<ReplyDTO> getList(@Param("cri")Criteria cri, @Param("boardNum") Long boardNum);
}
