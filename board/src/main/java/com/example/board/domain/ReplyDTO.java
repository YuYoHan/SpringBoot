package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReplyDTO {
    private Long replyNum;
    private String userId;
    private String replyContents;
    private String regDate;
    private String updateDate;
    private Long boardNum;
}
