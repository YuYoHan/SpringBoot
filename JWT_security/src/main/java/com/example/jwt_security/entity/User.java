package com.example.jwt_security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

// 데이터베이스 테이블과 1:1 매핑되는 객체를 뜻함
@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {


    @JsonIgnore
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name="userName", length = 50, unique = true)
    private String userName;

    @JsonIgnore
    @Column(name = "userPw", length = 100)
    private String password;

    @Column(name = "nickName", length = 50)
    private  String nickName;

    @JsonIgnore
    @Column(name = "activated")
    private boolean activated;


    // @ManyToMany와 @JoinTable은 User객체와 권한객체의 다대다 관계를
    // 일대다, 다대일 관계의 조인 테이블로 정했다는 뜻입니다.
    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;
}
