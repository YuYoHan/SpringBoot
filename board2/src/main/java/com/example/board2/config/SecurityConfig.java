package com.example.board2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // http basic 인증 방법을 비활성화
                .httpBasic().disable()
                // form login을 비활성화
                .formLogin().disable()
                // csf관련 설정을 비활성화
                .csrf().disable()
                // 세션 관리 정책을 설정
                // 세션을 유지하지 않도록 설정해줍니다.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 모든 URL에 대해 접근 허용
                .antMatchers("/**").permitAll();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // PasswordEncoder 구현체로 DelegatingPasswordEncoder를 사용해줍니다.
        // PasswordEncoderFactories 클래스에 팩토리 메소드를 이용하면 인스턴스를 생성할 수 있습니다.
        // 이것을 구현체로 사용한 이유는 비밀번호 암호화를 위한 다양한 알고리즘이 있는데
        // 이 구현체를 이용하면 여러 알고리즘들을 선택적으로 편리하게 사용할 수 있습니다.
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
