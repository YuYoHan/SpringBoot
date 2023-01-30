package com.example.demo.domain;

import lombok.Data;

@Data
public class MemberDTO {
    private long id;
    private String loginId;
    private String name;
    private String password;
}
