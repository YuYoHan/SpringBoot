package com.example.web_sty.dto;

import lombok.*;

@Getter
@Setter
@ToString
// 기본 생성자를 만들어줌
@NoArgsConstructor
// 필드를 모두 매개변수로 하는 생성자를 만들어준다.
@AllArgsConstructor
public class userDTO {
    private  Long id;
    private String userEmail;
    private String userPw;
    private String userName;
}
