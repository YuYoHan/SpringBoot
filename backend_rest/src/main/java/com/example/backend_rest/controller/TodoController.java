package com.example.backend_rest.controller;

import com.example.backend_rest.dto.ResponseDTO;
import com.example.backend_rest.dto.TodoDTO;
import com.example.backend_rest.model.TodoEntity;
import com.example.backend_rest.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {
    @Autowired
    private TodoService service;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = service.testService(); // 테스트 서비스 이용
        List<String> list = new ArrayList<>();
        list.add(str);

        // ResponseDTO를 제너릭으로 만들었기 때문에 <>가 붙는다.
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO) {
        try {
            String temporaryUserId = "temporary-user";

            // 1. TodoEntity로 변환
            TodoEntity entity = TodoDTO.todoEntity(todoDTO);

            TodoEntity entity1 = TodoEntity.builder()
                    // 2. id를 null로 초기화한다. 생성 당시에는 id가 없어야 하기 때문이다.
                    .id(null)
                    // 3. 임시 유저 아이디를 설정해준다.
                    // 지금은 인증과 인가 기능이 없으므로 한 유저만 로그인 없이 사용 가능한 애플리케이션이다.
                    .userId(temporaryUserId)
                    .build();

            // 4. 서비스를 이용해 Todo 엔티티를 생성한다.
            List<TodoEntity> entities = service.create(entity1);

            // 5. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
            /*
            *   stream :
            *       1. 원본 데이터를 읽기만하지, 원본 데이터를 변경을 하지 않는다.
            *       2. 정렬된 결과를 컬렉션이나 배열에 담아 반환 할 수 있다.
            *       3. 내부 반복문이며, 반복문이 코드상에 노출되지 않는다.
            * */
            List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

            // 6. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
            ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().data(dtos).build();

            // 7. ResponseDTO를 리턴한다.
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception e) {
            // 8. 혹시 예외가 나오면 dto 대신 error 메시지를 넣어 리턴한다.
            String error = e.getMessage();
            ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList() {
        String temporaryUserId = "temporary-user";

        // 1. 서비스 메서드의 retrieve 메서드를 사용해 Todo 리스트를 가져온다.
        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        // 2. 자바 스트림을 사용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
        List<TodoDTO> dtos = entities.stream().map(TodoDTO:: new).collect(Collectors.toList());

        // 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        // ResponseDTO를 리턴한다.
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
        String temporaryUserId = "temporary-user";

        // 1. dto를 entity로 변환
        TodoEntity todoEntity = TodoDTO.todoEntity(dto);
        // 2. id를 temporaryUserId로 초기화한다.
        TodoEntity entity1 = TodoEntity.builder()
                .userId(temporaryUserId).build();

        // 3. 서비스를 이용해 entity를 업데이트한다.
        List<TodoEntity> entities = service.update(entity1);

        // 4. 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환
        List<TodoDTO> dtos = entities.stream().map(TodoDTO :: new).collect(Collectors.toList());

        // 5. 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화한다.
        ResponseDTO<TodoDTO> responseDTO = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        // 6. ResponseDTO를 리턴
        return ResponseEntity.ok().body(responseDTO);
    }
}
