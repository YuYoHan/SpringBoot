package com.example.backend_rest.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "Todo")
public class TodoEntity {
    // 기본키가 될 필드에 지정
    @Id
    // Id를 자동으로 생성하겠다는 뜻
    // generator로 어떻게 Id를 생성할지 지정할 수 있다.
    // 여기서는 "system-uuid"로 했는데 이건
    // @GenericGenerator에 정의된 generator의 이름이다.
    @GeneratedValue(generator = "system-uuid")
    // Hibernate가 제공하는 기본 Generator이 아닌 나만의 Generator을
    // 사용하고 싶을 때 사용한다.
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;      // 이 오브젝트 아이디
    private String userId;  // 이 오브젝트를 생성한 아이디
    private String title;   // Todo 타이틀 (예_운동하기)
    private boolean done;   // true - todo 완료한 경우(checked)

    @Builder
    public TodoEntity(String id, String userId, String title, boolean done) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.done = done;
    }
}
