package com.example.backend_rest.service;

import com.example.backend_rest.model.TodoEntity;
import com.example.backend_rest.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
*   서비스 레이어는 컨트롤러와 퍼시스턴스 사이에서 비즈니스 로직을
*   수행하는 역할을 한다. 서비스 레이어는 HTTP와 긴밀히 연관된 컨트롤러에서
*   분리돼 있고, 또 데이터베이스와 긴밀히 연관된 퍼시스턴스와도 분리돼어 있다.
*   그래서 개발하고자 하는 로직에 집중 가능!
* */

// 스테레오타입 어노테이션
// 여기가 서비스 레이어라고 알려주는 어노테이션
@Service
@Slf4j
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    // 리팩토링한 메서드
    private void validate(final TodoEntity todoEntity) {
        if(todoEntity  == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if(todoEntity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
    }

    public String testService() {
        // TodoEntity 생성
        TodoEntity todoEntity = TodoEntity.builder().title("My first todo item").build();
        // TodoEntity 저장
        todoRepository.save(todoEntity);
        // TodoEntity 검색
        TodoEntity savedEntity = todoRepository.findById(todoEntity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity todoEntity) {
        // Validations
        validate(todoEntity);

        // save : 엔티티를 데이터베이스에 저장한다.
        todoRepository.save(todoEntity);

        log.info("Entity Id : {} is saved.", todoEntity.getId());

        // 저장된 엔티티를 포함해서 새 리스트를 리턴한다.
        return todoRepository.findByUserId(todoEntity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        log.info(userId);
        return todoRepository.findByUserId(userId);
    }

    public List<TodoEntity> update(final TodoEntity todoEntity) {
        // 저장할 엔티티가 유효한지 확인
        validate(todoEntity);

        // 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다.
        // 존재하지 않는 엔티티는 업데이트할 수 없기 때문이다.
        final Optional<TodoEntity> optionalTodoEntity = todoRepository.findById(todoEntity.getId());

        optionalTodoEntity.ifPresent(todo -> {
            // 변환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌운다.
            todo.builder()
                    .title(todoEntity.getTitle())
                    .done(todoEntity.isDone())
                    .build();

            // 데이터베이스에 새 값을 저장
            todoRepository.save(todo);
        });
        // Retrieve Todo에서 만든 메서드를 이용해 유저의 모든 Todo 리스트를 리턴한다.
        return retrieve(todoEntity.getUserId());
    }

    public List<TodoEntity> delete(TodoEntity entity) {
        validate(entity);

        try{
            todoRepository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity ", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }
}
