package com.example.rest_book1.controller;

import com.example.rest_book1.dto.PostsSaveRequestsDTO;
import com.example.rest_book1.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestsDTO postsSaveRequestsDTO) {
        return postsService.save(postsSaveRequestsDTO);
    }
}
