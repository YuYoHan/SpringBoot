package com.example.backend_rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id; // 유저에게 고유하게 부여되는 id

    @Column(nullable = false)
    private String username; // 아이디로 사용할 유저네임

    /*
    *   password가 null 가능이라 이상할 수 있다.
    *   OAuth를 사용하지 않을거면 이상한 것이 맞다.
    *   OAuth를 이용해 SSO(Single Sign On)을
    *   구현하지 않는다면 password는 null이면 안된다.
    *   하지만 SSO를 이용해 로그인하는 경우 password가 필요없다.
    *   따라서 데이터베이스에 password를 반드시 입력하도록 규제하면
    *   이후 SSO 구현 시 문제가 생기므로 null이 가능하도록 구현했다.
    * */
    private String password;
    private String role;    // 사용자의 롤. 예: 어드민, 일반사용자
    private String authProvider; // 이후 OAuth에서 사용할 유저 정보 제공자
}
