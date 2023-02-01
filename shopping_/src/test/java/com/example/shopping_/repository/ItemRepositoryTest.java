package com.example.shopping_.repository;

import com.example.shopping_.constant.ItemSellStatus;
import com.example.shopping_.entity.Item;
import com.example.shopping_.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.apache.juli.logging.Log;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.thymeleaf.util.StringUtils;

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

    // 상품 데이터를 만드는 새로운 메소드를 하나 만듭니다.
    // 1번부터 5번 상품은 상품의 판매상태를 SELL(판매 중)으로 지정하고
    // 6번부터 10번까지는 판매상태를 SOLD_OUT(품절)로 세팅해 생성합니다.
    public void createItemList2() {
        for (int i = 1; i < 5; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명 " +i);
            item.setItemSellStatus(ItemSellStatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }

        for (int i = 6; i < 10; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명 " +i);
            item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            itemRepository.save(item);
        }
    }


    @Test
    @DisplayName("상품 Querydsl 조회 테스트2")
    public void queryDslTest2() {
        this.createItemList2();

        // BooleanBuilder는 쿼리에 들어갈 조건을 만들어주는 빌더라고 생각하면 됩니다.
        // Predicate를 구현하고 있으며 메소드 체인 형식으로 사용할 수 있습니다.
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStat ="SELL";

        // 필요한 상품을 조회하는데 필요한 "and" 조건을 추가하고 있습니다.
        // 아래 소스에서 상품의 판매상태가 SELL일 때만 booleanBuilder에 판매상태 조건을 동적으로 추가흔 것을 볼수 있다.
        booleanBuilder.and(item.itemDetail.like("%" + itemDetail +"%"));
        booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStat, ItemSellStatus.SELL)) {
            booleanBuilder.and(item.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        // 데이터를 페이징해 조회하도록 PageRequest.of() 메소드를 이용해 Pageble 객체를 생성
        // 첫 번째 인자는 조회활 페이지 정보, 두 번째 인자는 한 페이지당 조회할 데이터 개수를 넣어줍니다.
        Pageable pageable = PageRequest.of(0, 5);

        // QueryDslPredicateExecutor 인터페이스에서 정의한 findAll() 메소드를 이용해 조건에 맞는 데이터를
        // Page 객체로 받아옵니다.
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + itemPagingResult.getTotalElements());

        List<Item> resultItemList = itemPagingResult.getContent();

        for (Item resultItem: resultItemList
             ) {
            System.out.println(resultItem.toString());
        }
    }
}