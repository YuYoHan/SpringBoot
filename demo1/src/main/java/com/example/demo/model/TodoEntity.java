package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Builder는 오브젝트 생성을 위한 디자인 패턴 중 하나
// @Builder 어노테이션을 사용하면 우리가 Builder 클래스를 따로 개발하지 않고도
// Builder 패턴을 사용해 오브젝트를 생성할 수 있다.
@Builder
// 매개변수가 없는 생성자를 구현해준다.
@NoArgsConstructor
// 클래스의 모든 멤버변수를 매개변수로 받는 생성자를 구현해준다.
@AllArgsConstructor
@Data
public class TodoEntity {
    private String id;          // 이 오브젝트 아이디
    private String userId;      // 이 오브젝트를 생성한 유저의 아이디
    private String title;       // Todo 타이틀
    private boolean done;       // true - todo를 완료한 경우
}
