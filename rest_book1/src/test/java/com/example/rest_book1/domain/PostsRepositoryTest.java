package com.example.rest_book1.domain;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Log4j2
class PostsRepositoryTest {


    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp() {
        postsRepository.deleteAll();
    }

    @Test
    @DisplayName("게시글 저장 불러오기")
    public void saveBoardTest() {
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // 테이블에 posts에 insert/update 쿼리를 실행합니다.
        // id 값이 있다면 update, 없다면 insert 쿼리가 실행됩니다.
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("tester@naver.com")
                .build());

        // when
        // 테이블 posts에 있는 모든 데이터를 조회해오는 메소드입니다.
        List<Posts> postsList = postsRepository.findAll();

        // then
        Posts posts = postsList.get(0);
        log.info("게시물 보기 : " +posts);
        Assertions.assertThat(posts.getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.getContent()).isEqualTo(content);
    }
}