package com.example.board2.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
//  MemberRole은 Member에서 Set으로 저장되기 때문에, equals와 hashcode를 재정의해주었습니다.
@EqualsAndHashCode
// 여러개의 필드를 primary key로 사용하기위해 @IdClass를 선언했습니다.
// MemberRoled 클래스에 정의된 필드와 동일한 필드를, MemberRole에서 @Id로 선언해 주면
// composite key로 만들 수 있습니다.
@IdClass(MemberRoleId.class)
public class MemberRole {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;
}
