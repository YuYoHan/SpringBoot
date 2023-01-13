package com.example.study01.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BoardDTO {
    private long boardNum;
    private String boardTitle;
    private String boardContents;
    private String userId;
    private Date regDate;
    private Date updateDate;
}
