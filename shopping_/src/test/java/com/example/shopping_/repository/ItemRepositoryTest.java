package com.example.shopping_.repository;

import com.example.shopping_.constant.ItemSellStatus;
import com.example.shopping_.entity.Item;
import com.example.shopping_.entity.QItem;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    // 영속성 컨텍스트를 이용하기 위해 @PersistenceContext 어노테이션을 이용해
    // EntityManager 빈을 주입합니다.
    @PersistenceContext
    // Entity를 관리하는 역할을 수행하는 클래스
    // EntityManager 내부에 영속성 컨텍스트를 이용하여 관리한다.
    // Transaction 단위를 수행할 때마다 생성된다.
    // 요청 시 생성되며 Transaction 후에는 close()되어야 한다.
    EntityManager em;


    @Test
    @DisplayName("상품 저장 테스트")
    public void createItemTest() {
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem.toString());
    }

    @Test
    @DisplayName("상품리스트 테스트")
    public void createItemList() {
        for (int i = 1; i < 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for (Item item: itemList
             ) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest() {
        // 기존에 만들었던 테스트 상품을 만드는 메소드를 실행하여 조회할 대상 만듬
        this.createItemList();
        // 상품명이 "테스트 상품1" 또는 상품 상세 설명이 "테스트 상품 상세 설명5"이면
        // 해당 상품을 itemList에 할당합니다.
        // 테스트 코드를 실행하면 조건대로 2개의 상품이 출력됩니다.
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
        for (Item item: itemList
             ) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        // 현재 데이터베이스에 저장된 가격이 10001 ~ 10010입니다.
        // 테스트 코드 실행 시 10개의 상품을 저장하는 로그가 콘솔에 나타나고
        // 맨 마지막에 가격이 10005보다 작은 4개의 상품을 출력해줍니다.
        List<Item> itemList = itemRepository.findByPriceLessThan(10005);
        for (Item item: itemList
             ) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDescTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for (Item item: itemList
             ) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상세 설명");
        for (Item item: itemList
             ) {
            System.out.println(item.toString());
        }
    }

    @Test
    @DisplayName("Querydsl 조회 테스트1")
    public void queryDslTest() {
        this.createItemList();
        // JPAQueryFactory를 이용하여 쿼리를 동적으로 생성합니다.
        // 생성자의 파라미터로는 EntityManager 객체를 넣어줍니다.
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        // Querydsl을 통해 쿼리를 생성하기 위해 플러그인을 통해 자동으로 생성된 QItem 객체를 이용합니다.
        QItem qItem = QItem.item;
        // 자바 소스코드지만 SQL문과 비슷하게 소스를 작성할 수 있습니다.
        JPAQuery<Item> query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "테스트 상품 상세 설명" + "%"))
                .orderBy(qItem.price.desc());

        // JPAQuery 메소드 중 하나인 fetch를 이용해서 쿼리 결과를 리스트로 반환합니다.
        // fetch() 메소드 실행 시점에 쿼리문이 실행됩니다.
        List<Item> itemList = query.fetch();

        for (Item item: itemList
             ) {
            System.out.println(item.toString());
        }
    }
}