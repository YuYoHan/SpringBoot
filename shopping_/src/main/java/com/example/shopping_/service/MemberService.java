package com.example.shopping_.service;

import com.example.shopping_.entity.Member;
import com.example.shopping_.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.Validate;

@Service
// 비즈니스 로직을 담당하는 서비스 계층 클래스에
// @Transactional 어노테이션을 선언합니다.
// 로직을 처리하다가 에러가 발생하면
// 변경된 데이터 로직을 처리하기 전으로 콜백해줍니다.
@Transactional
// 빈 주입 방법중 한 개인데
// @NonNull 이나 final 붙은 필드에 생성자를 생성
@RequiredArgsConstructor
public class MemberService {

    // 생성자가 1개이므로 @Autowired를 생략 가능
    private final MemberRepository memberRepository;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    // saveMember 메소드가 실행될 때 member 매개변수가 넘어오면
    // validateDuplicateMember(Member member)가 실행되고
    // 매개변수를 받아와서 레포지토리에서 만든 findByEmail을 실행해서
    // 이메일이 있는지 검사하고 findMember에 넣어줍니다.
    // 그리고 빈값이 아니면 IllegalStateException 예외를 발생시켜줍니다.
    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
}
