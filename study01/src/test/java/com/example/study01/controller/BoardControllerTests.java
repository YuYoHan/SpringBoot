package com.example.study01.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Log4j2
@ExtendWith(SpringExtension.class)
//// Test for controller
//// Spring Framework에서 애플리케이션 컨텍스트의 웹 버전을 생성하는 데 사용되는 클래스 레벨 어노테이션
//// 테스트를 위해 부트 스트랩 된 ApplicationContext 가 WebApplicationContext 의 인스턴스 여야 함 을 나타내는 데 사용됩니다 .
//@WebAppConfiguration

// 테스트 케이스가 실행될 때 테스트에 필요한 모든 설정과 빈들을 자동으로 초기화하는 역할을 수행한다.
//@SpringBootTest

@WebMvcTest
public class BoardControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testList() throws Exception{
    }
}
