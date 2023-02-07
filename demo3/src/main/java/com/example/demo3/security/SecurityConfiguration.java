package com.example.demo3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 설정한 리소스의 접근을 인증절차 없이 허용
                    .antMatchers("/", "/home").permitAll()
                // 그 외 모든 리소스를 의미하며 인증 필요
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login") // 기본 로그인 페이지
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //  withDefaultPasswordEncoder 메소드는 Deprecated 되어 있으며
        //  실제 운영서버에서는 사용하지 말아야 한다. 샘플코드에서만 다루고
        //  다음 시간부터는 사용하지 않을 예정이다.
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        // 메모리에 사용자 정보를 담는다.
        return new InMemoryUserDetailsManager(user);
    }

}
