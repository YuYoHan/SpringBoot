package com.example.board2.entity;

import com.example.board2.common.EntityDate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
@Getter
// Member는 필드가 지정된 생성자를 사용하여 생성할 수 있습니다.
// 인스턴스가 불완전한 상태에 있음을 방지하고자
// 기본 생성자는 외부로 노출할 필요가 없습니다. 하지만 JPA 명세에서는
// 엔티티에 기본 생성자를 요구하기 때문에, 기본 생성자는 접근 제어 레벨을
// PROTECTED로 설정해 두었습니다.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// EntityDate는 데이터가 생성된 시간, 수정된 시간을 자동으로 업데이트해주기 위해 사용
public class Member extends EntityDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    // email과 nickName을 보면 unique로 설정했고
    // 이로인해, 인덱스가 형성되고 중복을 허용하지 않는 제약조건이 추가되었습니다.
    @Column(nullable = false, length = 30, unique = true)
    private String email;

    // password는 Not null의 제약조건을 걸려있지 않는데
    // 나중에 추가될 수 있는 소셜 로그인을 염두
    private String password;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(nullable = false, unique = true, length = 20)
    private String nickName;

    // 사용자를 나타내는 Member 엔티티와 권한 등급을 나타내는 Role 엔티티 간의
    // 브릿지 테이블을 MemberRole 엔티티로 정의했습니다.
    // 한명의 사용자는 여러 개의 권한을 가질 수 있고 여러개의 권한은 여러 사용자가
    // 가지고 있을 수 있습니다. 이를 Member과 Role 간에 @ManyToMany로 설정하면
    // 브릿지 테이블을 위한 엔티티를 별도로 선언하지 않아도 나타낼 수도 있지만,
    // 사용자가 가진 권한에 대해 어떤 추가적인 데이터가 기록될지 모르기 때문에
    // 이에 대한 유연성을 위하여 @OneToMany로 직접 선언하여 명시했습니다.
    //  Member가 저장될 때 MemberRole 또한 연쇄적으로 저장되거나 제거될 수 있도록 cascade 옵션을 ALL로,
    //  orphanRemoval=true로 설정해줍니다. Member가 제거되거나 연관 관계가 끊어져서 MemberRole이 고아 객체가 되었을 때,
    //  MemberRole은 Member와 생명 주기를 함께하며 제거될 것입니다. 실제로 각 사용자가 가질 수 있는 권한 등급은, 그렇게 많지는 않겠지만,
    //  우리의 애플리케이션으로 조회 했을 때의 검색 성능 향상을 위해 Set으로 선언하였습니다.
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MemberRole> roles;

    public Member(String email, String password, String userName, String nickName, Set<MemberRole> roles) {
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.nickName = nickName;
        this.roles = roles.stream().map(r -> new MemberRole(this, r.getRole())).collect(toSet());
    }

    // 닉네임을 업데이트 할 수 있는 요구사항을 충족하기 위해, 업데이트 메소드를 작성
    public void updateNickName(String nickName) {
        this.nickName = nickName;
    }
}
