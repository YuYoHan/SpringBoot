package com.example.rest_book1.dto;

import com.example.rest_book1.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestsDTO {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestsDTO(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }



    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
