package com.example.rest_book1.service;

import com.example.rest_book1.domain.Posts;
import com.example.rest_book1.domain.PostsRepository;
import com.example.rest_book1.dto.PostsResponseDTO;
import com.example.rest_book1.dto.PostsSaveRequestsDTO;
import com.example.rest_book1.dto.PostsUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestsDTO postsSaveRequestsDTO) {
        return postsRepository.save(postsSaveRequestsDTO.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDTO requestsDTO) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));

        posts.update(requestsDTO.getTitle(), requestsDTO.getContent());

        return id;
    }

    public PostsResponseDTO findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));

        return new PostsResponseDTO(entity);
    }
}
