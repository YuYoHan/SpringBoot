package com.example.study01.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user")
// 기본 생성자를 자동으로 만들어줌
@NoArgsConstructor
// 필드를 모두 매개 변수로 하는 생성자를 만들어준다.
@AllArgsConstructor
@ToString
@Getter
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(length = 200, nullable = false)
    private String userEmail;
    @Column(length = 200, nullable = false)
    private String userPw;
    @Column(length = 200, nullable = false)
    private String userName;
}
