package com.example.demo.service;


import com.example.demo.domain.MemberDTO;
import com.example.demo.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private  final MemberRepository memberRepository;

    public MemberDTO Login(String loginId, String password) {
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
