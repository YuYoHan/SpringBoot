package com.example.rest_book1.service;

import com.example.rest_book1.domain.PostsRepository;
import com.example.rest_book1.dto.PostsSaveRequestsDTO;
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

    public Long update(Long id, PostsSaveRequestsDTO requestsDTO) {

    }
}
