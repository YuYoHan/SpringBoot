package com.example.hellospring.service;

import com.example.hellospring.domain.Member;
import com.example.hellospring.repository.MemberRepositrory;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    private final MemberRepositrory mr;

//    @Autowired
    public MemberService(MemberRepositrory mr) {
        this.mr = mr;
    }

    // 회원가입
    public long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
        validateDuplicateMember(member); // 중복 회원 검증
        mr.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        mr.findByName(member.getName())
            .ifPresent(m ->  {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
         return mr.findAll();
    }

    public Optional<Member> findOne(long memberId) {
        return mr.findById(memberId);
    }


}
