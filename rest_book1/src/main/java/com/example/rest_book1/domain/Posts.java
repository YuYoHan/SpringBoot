package com.example.rest_book1.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity클래스라고 합니다.
// JPA를 사용하면 DB 데이터에 작업을 할경우 실제 쿼리를 날리기 보다는
// 이 Entity 클래스의 수정을 통해 작업을 합니다.

@Getter
@NoArgsConstructor
// JPA 어노테이션
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private  String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
