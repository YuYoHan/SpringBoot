package com.example.board.service;

import com.example.board.domain.Criteria;
import com.example.board.domain.ReplyDTO;
import com.example.board.domain.ReplyPageDTO;
import com.example.board.mapper.ReplyMapper;

public interface ReplyService {
    boolean regist(ReplyDTO reply);
    boolean remove(Long replyNum);
    boolean modify(ReplyDTO reply);
    ReplyPageDTO getList(Criteria cri, Long boardNum);
}
