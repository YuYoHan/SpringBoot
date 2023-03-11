package com.example.backend_rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class TodoDTO {
    private String id;
    private String userId;
    private boolean done;
}
