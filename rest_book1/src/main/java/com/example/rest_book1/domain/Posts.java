package com.example.rest_book1.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

// 실제 DB의 테이블과 매칭될 클래스이며 보통 Entity클래스라고 합니다.
// JPA를 사용하면 DB 데이터에 작업을 할경우 실제 쿼리를 날리기 보다는
// 이 Entity 클래스의 수정을 통해 작업을 합니다.

@Getter
@ToString
// 기본 생성자
@NoArgsConstructor
@Table(name = "posts")
// JPA 어노테이션
// 테이블과 링크될 클래스임을 나타냄
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
@Entity
public class Posts extends BaseTimeEntity {

    // 해당 테이블의 PK를 나타냄
    @Id
    // PK 생성 규칙을 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 해당 클래스의
    // 필드는 모두 칼럼이 됩니다.
    // 기본값외에도 추가로 변경이 필요한 옵션이 있으면 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private  String author;

    // 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
