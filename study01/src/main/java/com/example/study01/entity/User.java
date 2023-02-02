package com.example.study01.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user")
// 기본 생성자를 자동으로 만들어줌
@NoArgsConstructor
// 필드를 모두 매개 변수로 하는 생성자를 만들어준다.
@AllArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(length = 200, nullable = false)
    private Long id;

    private Long userId;
    private String userEmail;
    private String userPw;
    private String userName;
}
