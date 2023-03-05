package com.example.rest_book1.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 테스트를 진행할때 JUnit에 내장된 실행자외에 다른 실행자를 실행시킨다.
// 여기서는 SpringExtension라는 실행자를 사용
// 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
// JUnit4에서는 @RunWith였지만 5에서는 @ExtendWith다.
@ExtendWith(SpringExtension.class)
// 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
// 선언할 경우 @Controller, @ControllerAdivce 등을 사용 가능
// 단, @Service, @Component, @Repository 등은 사용할 수 없습니다.
// 여기서는 컨트롤러만 사용하기 때문에 선언
@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    // 스프링이 관리하는 Bean을 주입 받습니다.
    @Autowired
    // 웹 API를 테스트할 때 사용
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있습니다.
    private MockMvc mvc;

    @Test
    void hello() throws Exception {
        String hello = "hello";

        // MockMvc를 통해 /hello주소로 HTTP GET 요청을 합니다.
        // 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 가능
        mvc.perform(MockMvcRequestBuilders.get("/hello"))
                // mvc.perform 결과를 검증
                // HTTP Header의 Status를 검증
                // 우리가 흔히 알고 있는 200, 400, 500 등의 상태를 검증
                // 여기서는 OK 즉, 200인지 아닌지를 검증
                .andExpect(status().isOk())
                // 응답 본문의 내용을 검증
                // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
                .andExpect(content().string(hello));
    }
}