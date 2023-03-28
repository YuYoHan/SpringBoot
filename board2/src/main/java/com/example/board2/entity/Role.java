package com.example.board2.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Role {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    // RoleType은 어떤 권한 등급이 있는지 나타내는 Enum 클래스입니다.
    // EnumType.STRING으로 지정해줌으로써 데이터베이스에 저장할 때,
    // 문자열로 저장하게 됩니다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType roleType;

    public Role(RoleType roleType){
        this.roleType = roleType;
    }
}
