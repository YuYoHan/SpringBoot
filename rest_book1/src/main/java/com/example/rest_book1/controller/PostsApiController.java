package com.example.rest_book1.controller;

import com.example.rest_book1.dto.PostsSaveRequestsDTO;
import com.example.rest_book1.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestsDTO postsSaveRequestsDTO) {
        return postsService.save(postsSaveRequestsDTO);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsSaveRequestsDTO requestsDTO) {
        return postsService.update(id,requestsDTO);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDTO findById(@PathVariable Long id) {
        return postsService.findById(id);
    }
}
