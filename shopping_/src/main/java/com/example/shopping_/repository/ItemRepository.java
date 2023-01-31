package com.example.shopping_.repository;

import com.example.shopping_.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNm(String itemNm);

    // 상품을 상품명과 상품 상세 설명을 OR 조건을 이용하여 조회하는 쿼리 메소드입니다.
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
}
