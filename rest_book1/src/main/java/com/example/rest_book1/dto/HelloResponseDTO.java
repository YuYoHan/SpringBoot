package com.example.rest_book1.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
// 선언된 모든 final 필드가 포함된 생성자를 생성
// final이 없는 필드는 생성자에 포함하지 않는다.
@RequiredArgsConstructor
public class HelloResponseDTO {

    private final String name;
    private final int amount;
}
