package com.example.backend_rest.persistence;

import com.example.backend_rest.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    List<TodoEntity> findByUserId(String userId);

    // ?1은 메서드의 매개변수의 순서 위치이다.
//    @Query("select * from TodoEntity t where t.userId = ?1")
//    List<TodoEntity> findByUserIdQuery(String userId);
}
