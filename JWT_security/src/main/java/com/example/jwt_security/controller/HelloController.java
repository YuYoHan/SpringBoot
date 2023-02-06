package com.example.jwt_security.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/hello")
    // ResponseEntity란, httpentity를 상속받는, 결과 데이터와 HTTP 상태 코드를 직접 제어할 수 있는 클래스이다.
    // ResponseEntity에는 사용자의  HttpRequest에 대한 응답 데이터가 포함된다.
    // 스프링에서 제공하며 header와 body를 포함하고, 추가로 HttpStatus 코드가지 함께 축가할 수 있는 클래스
    // REST 컨트롤러 혹은 일반 컨트롤러에서 응답하는 객체로서 사용된다.
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("hello");
    }
}
