package com.example.rest_book1.controller;

import com.example.rest_book1.domain.Posts;
import com.example.rest_book1.domain.PostsRepository;
import com.example.rest_book1.dto.PostsSaveRequestsDTO;
import com.example.rest_book1.dto.PostsUpdateRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Log4j2
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("Posts_등록된다 테스트")
    public void test1() throws Exception {
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestsDTO requestsDTO = PostsSaveRequestsDTO.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        // 기본 http 헤더를 사용하며 결과를 ResponseEntity로 반환받는다.
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestsDTO, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("Posts_수정된다 테스트")
    public void test2() {
        // given
        Posts savedPosts = postsRepository.save(Posts.builder()
                        .title("title")
                        .content("content")
                        .author("author")
                .build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDTO requestDTO = PostsUpdateRequestDTO.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        // HttpEntity클래스는 HTTP요청또는 응답에 해당하는 HttpHeader와 HttpBody를 포함하는 클래스다.
        HttpEntity<PostsUpdateRequestDTO> requestEntity = new HttpEntity<>(requestDTO);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(
                url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        log.info("-----------------------------------");
        log.info("수정확인 : " + all);
        Assertions.assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        Assertions.assertThat(all.get(0).getContent()).isEqualTo(expectedContent);

    }


}