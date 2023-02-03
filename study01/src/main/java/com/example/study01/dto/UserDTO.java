package com.example.study01.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userEmail;
    private String userPw;
    private String userName;
}
