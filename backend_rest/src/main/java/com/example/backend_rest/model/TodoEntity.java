package com.example.backend_rest.model;

import lombok.*;

@NoArgsConstructor
@Getter
@ToString
public class TodoEntity {

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
