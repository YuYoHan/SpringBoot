package com.example.shopping_.service;

import com.example.shopping_.DTO.MemberFormDTO;
import com.example.shopping_.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
// 테스트 실행 후 롤백 처리를 해줘서 반복적으로 테스트 가능
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    // 회원 정보를 입력한 Member 엔티티를 만드는 메소드를 작성
    public Member createMember() {
        MemberFormDTO memberFormDTO = new MemberFormDTO();
        memberFormDTO.setEmail("test@naver.com");
        memberFormDTO.setName("테스터");
        memberFormDTO.setAddr("서울시 마포구 합정동");
        memberFormDTO.setPassword("1234");
        return Member.createMember(memberFormDTO, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입 테스트")
    public void saveMemberTest() {
        Member member = createMember();
        Member saveMember = memberService.saveMember(member);

        Assertions.assertThat(member.getEmail()).isEqualTo(saveMember.getEmail());
        Assertions.assertThat(member.getName()).isEqualTo(saveMember.getName());
        Assertions.assertThat(member.getAddr()).isEqualTo(saveMember.getAddr());
        Assertions.assertThat(member.getPassword()).isEqualTo(saveMember.getPassword());
        Assertions.assertThat(member.getRole()).isEqualTo(saveMember.getRole());
    }
}
