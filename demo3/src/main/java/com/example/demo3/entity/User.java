package com.example.demo3.entity;

import com.example.demo3.DTO.UserDTO;
import lombok.*;

import javax.persistence.*;

@Entity
// 테이블 이름 정하기
@Table(name = "user")
// 기본 생성자를 자동으로 만들어줌
@NoArgsConstructor
// 필드를 모두 매개 변수로 하는 생성자를 만들어준다.
@AllArgsConstructor
@ToString
@Getter
@Builder
@Setter
public class User {

    // PK
    @Id
    // auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // unique 제약조건
    @Column(unique = true)
    private String userEmail;
    @Column(length = 200, nullable = false)
    private String userPw;
    @Column(length = 200, nullable = false)
    private String userName;

    public static User toUserEmtity(UserDTO user) {
        User userentity = new User();
        userentity.setUserEmail(user.getUserEmail());
        userentity.setUserPw(user.getUserPw());
        userentity.setUserName(user.getUserName());
        return userentity;
    }
}
