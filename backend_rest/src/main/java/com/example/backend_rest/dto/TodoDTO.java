package com.example.backend_rest.dto;

import com.example.backend_rest.model.TodoEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;

    @Builder
    public TodoDTO(final TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        // isDone인 이유는 set은 setDone인데 get은 isDone이다.
        this.done = todoEntity.isDone();
    }

    public static TodoEntity todoEntity(final TodoDTO todoDTO) {
        return TodoEntity.builder()
                .id(todoDTO.getId())
                .title(todoDTO.title)
                .done(todoDTO.isDone())
                .build();
    }

}
