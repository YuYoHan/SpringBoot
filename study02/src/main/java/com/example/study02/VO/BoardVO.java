package com.example.study02.VO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class BoardVO {
    private long boardNum;
    private String boardType;
    private String title;
    private String contents;
    private String userId;
    private Date createTime;
    private Date updateTime;
    private String likes;
    private String counts;
}
