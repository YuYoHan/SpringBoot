package com.example.backend_rest.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ResponseDTO<T> {
    private String error;
    private List<T> data;
}
