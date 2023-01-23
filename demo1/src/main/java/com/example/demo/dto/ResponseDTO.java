package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO {
    private String error;
    // TodoDTO뿐만 아니라 이후 다른 모델의 DTO도 ResponseDTO를 이용해 리턴할 수 있도록
    // 자바 Generic을 이용햇다.
//    private List<T> data;
}
