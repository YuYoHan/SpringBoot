package com.example.shopping_.entity;

import com.example.shopping_.constant.ItemSellStauts;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Item {
    // 상품코드
    private long id;
    // 상품명
    private String itemNum;
    // 가격
    private int price;
    // 재고수량
    private int stockNumber;
    // 상품 상세 설명
    private String itemDetail;
    // 상품 판매 상태
    private ItemSellStauts itemSellStauts;
    // 등록 시간
    // LocalDateTime 타입은 현재 로컬 컴퓨터의 날짜와 시간을 반환
    private LocalDateTime regTime;
    // 수정 시간
    private LocalDateTime updateTime;
}
