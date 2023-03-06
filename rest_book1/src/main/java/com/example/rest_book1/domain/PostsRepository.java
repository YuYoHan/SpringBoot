package com.example.rest_book1.domain;

import com.example.rest_book1.domain.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
