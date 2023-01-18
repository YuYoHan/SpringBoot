package com.example.study01.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

    private int pageNum;
    private int amount;

    // Criteria 클래스 용도는 pageNum과 amount 값을 같이 전달하는 용도지만
    // 생성자를 통해서  기본값을 1페이지, 10개로 지정해서 처리합니다.
    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }
}
