package com.example.hellospring.domain;

import lombok.Data;

@Data
public class UserDTO {
    private String userId;
    private String userPw;
    private String userName;
}
