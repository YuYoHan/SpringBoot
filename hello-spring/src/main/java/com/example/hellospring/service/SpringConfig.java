package com.example.hellospring.service;

import com.example.hellospring.repository.MemberRepositrory;
import com.example.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepositrory());
    }

    @Bean
    public MemberRepositrory memberRepositrory() {
        return new MemoryMemberRepository();
    }
}
