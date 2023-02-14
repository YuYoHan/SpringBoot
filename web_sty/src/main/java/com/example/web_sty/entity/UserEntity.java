package com.example.web_sty.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "user")
public class UserEntity {

    // pk
    @Id
    // auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userEmail;

    @Column
    private String userPw;

    @Column
    private String userName;
}
