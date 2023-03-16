package com.example.backend_rest.service;

import com.example.backend_rest.model.TodoEntity;
import com.example.backend_rest.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if(todoEntity == null) {
            log.warn("Entity cannot be null");
            throw new RuntimeException("Entity cannot be null");
        }

        if(todoEntity.getUserId() == null) {
            log.warn("Unknown user");
            throw new RuntimeException("Unknown user");
        }
        // save : 엔티티를 데이터베이스에 저장한다.
        todoRepository.save(todoEntity);

        log.info("Entity Id : {} is saved.", todoEntity.getId());

        // 저장된 엔티티를 포함해서 새 리스트를 리턴한다.
        return todoRepository.findByUserId(todoEntity.getUserId());
    }
}
