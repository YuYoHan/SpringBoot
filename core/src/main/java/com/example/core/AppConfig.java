package com.example.core;

import com.example.core.member.MemberService;
import com.example.core.member.MemberServiceImpl;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    public MemberService memberService() {
        return new MemberServiceImpl();
    }

}
