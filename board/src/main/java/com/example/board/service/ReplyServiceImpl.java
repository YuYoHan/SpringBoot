package com.example.board.service;

import com.example.board.domain.Criteria;
import com.example.board.domain.ReplyDTO;
import com.example.board.domain.ReplyPageDTO;
import com.example.board.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private ReplyMapper replyMapper;

    @Override
    public boolean regist(ReplyDTO reply) {
        return false;
    }

    @Override
    public boolean remove(Long replyNum) {
        return false;
    }

    @Override
    public boolean modify(ReplyDTO reply) {
        return false;
    }

    @Override
    public ReplyPageDTO getList(Criteria cri, Long boardNum) {
        return new ReplyPageDTO(replyMapper.getTotal(boardNum), replyMapper.getList(cri, boardNum));
    }
}
