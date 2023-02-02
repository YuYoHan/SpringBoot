package com.example.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.util.UriComponentsBuilder;

@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum;
    private int amount;
    private String type;
    private String keyword;
    private int startrow;

    public Criteria() {
        // this() : 현재 클래스 생성자
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
        this.startrow =0;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
        this.startrow = (this.pageNum -1) * this.amount;
    }


    public String[] getTypeArr() {
        // type이 null이라면 return {}
        // type에 "TC"가 있다면 return {"T", "C"}
        return type == null ? new String[] {} : type.split("");
    }

    public String getListLink() {
        // ? 앞에 오는 uri 문자열
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
                // 파라미터 추가
                .queryParam("pageNum", pageNum)
                .queryParam("amount", amount)
                .queryParam("keyword", keyword)
                .queryParam("type", type);
        // ?pageNum=3&amount=10&keyword=app&type=TC
        // 빌더가 가지고 있는 설정대로 문자열 만들기
        return builder.toUriString();
    }
}
