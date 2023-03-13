package com.example.backend_rest.service;

import org.springframework.stereotype.Service;

/*
*   서비스 레이어는 컨트롤러와 퍼시스턴스 사이에서 비즈니스 로직을
*   수행하는 역할을 한다. 서비스 레이어는 HTTP와 긴밀히 연관된 컨트롤러에서
*   분리돼 있고, 또 데이터베이스와 긴밀히 연관된 퍼시스턴스와도 분리돼어 있다.
*   그래서 개발하고자 하는 로직에 집중 가능!
* */

// 스테레오타입 어노테이션
// 여기가 서비스 레이어라고 알려주는 어노테이션
@Service
public class TodoService {

    /*
    *   service를 만들었으니 TodoController 작성
    * */
    public String testService() {
        return "Test Service";
    }
}
