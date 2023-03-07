package com.example.rest_book1.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class IndexControllerTest {
    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    @DisplayName("메인페이지 로딩")
    public void mianTest() {
        // when
        String body = this.testRestTemplate.getForObject("/", String.class);

        // then
        Assertions.assertThat(body).contains("스프링부트로 시작하는 웹 서비스");
    }

}