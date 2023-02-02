package com.example.study01.entity;

import lombok.*;

import javax.persistence.*;

// Spring DATA JPA에서는 @Entity 추가해야한다.
// 해당 클래스가 엔티티를 위한 클래스라는 것을 증명
// 해당 클래스의 인스턴스들이 JPA로 관리되고 있다는 것을 의미
// 이 어노테이션이 붙으면 옵션에 따라 자동으로 테이블을 생성하고 변수에 따라 컬럼도 자동으로 생성
@Entity
// 어떤 테이블로 생성할 것인지 담아둔 어노테이션
@Table(name = "tb1_memo")
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo {
    // PK에 해당하는 특정 필드를 @Id로 지정해야 한다.
    // 자동으로 생성되는 번호를 사용하기 위해서는 @GeneratedValue라는 어노테이션을 사용
    @Id
    // PK를 자동 생성할 때 사용한다.
    // Auto : JPA 구현체가 생성 방식을 결정
    // IDENTITY : 사용하는 데이터베이스가 키 생성을 결정
    // SEQUENCE : 데이터베이스의 sequence를 이용해서 키를 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
