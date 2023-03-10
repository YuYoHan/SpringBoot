package com.example.rest.controller;

import com.example.rest.DTO.Post;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {

    // 게시글 추가
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    //ResponseEntity 상태코드 제어
    public ResponseEntity<Void> createPost(@RequestBody Map<String, Object> requestBody) {
        log.info("createPost");
        // 정상적으로 수행됐다고 상태 리턴(200)
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 게시글 목록 조회
    @GetMapping("")
    public List<Post> getPostList(@RequestParam(value = "postId", required = false) String postId) {
        // 게시글 데이터가 조회가 되려면 데이터가 있어야 하는데 없으므로 임의로 10개만 생성해줌
        // Post 객체로 ArrayList를 생성
        ArrayList<Post> posts = new ArrayList<Post>();

        for (int i = 0; i < 10; i++) {
            // Post 객체를 생성해서 데이터를 title과 contents에 postId만 붙여서 넣어줌
            Post post = new Post();
            post.setPostId(Integer.toString(i));
            post.setTitle("title" + i);
            post.setContents("content" + i);
            posts.add(post);
        }
        return posts;
    }

    // 특정 게시글 조회
    // Get요청이 들어왔을 때, value= "/{postId}"는 postId 게시글의 데이터를 조회하기 위해 설정
    // ex) localhost:8080/posts/1
    @GetMapping("/{postId}")
    // PathVariable은 URI에 넘어온 postId 값을 가져오기 위해 사용
    public Post getPost(@PathVariable String postId) {
        // 데이터베이스에 연동이 따로 되어 있는 것이 없기 때문에 임시적으로 객체를 생성해서 넘겨줌
        return new Post(postId, "title"+postId, "contents"+postId);
    }
}
