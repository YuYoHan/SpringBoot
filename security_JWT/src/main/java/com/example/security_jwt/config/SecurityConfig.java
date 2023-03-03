package com.example.security_jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// 스프링 시큐리티 필터가 스프링 필터체인에 등록이 됩니다.
@EnableWebSecurity
public class SecurityConfig {


    /*
    *   @Override
    *   protected void cofigure(HttpSecurity http) throws Exception{}
    *
    *   ↓아래와 같이 바뀜↓
    * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // .csrf() : Cross site Request forgery로 사이즈간 위조 요청인데
        // 즉 정상적인 사용자가 의도치 않은 위조요청을 보내는 것을 의미한다.
        /*
        *   예를 들어 A라는 도메인에서, 인증된 사용자 H가 위조된
        *   request를 포함한 link, email을 사용하였을 경우(클릭, 또는 사이트 방문만으로도),
        *   A 도메인에서는 이 사용자가 일반 유저인지, 악용된 공격인지 구분할 수가 없다.
        * */
         http.csrf().disable();
         // 특정한 경로에 특정한 권한을 가진 사용자만 접근할 수 있도록 아래의 메소드를 이용합니다.
         http.authorizeRequests()
                 // antMatchers()는 특정한 경로를 지정합니다.
                 .antMatchers("/user/**").authenticated()
                 // hasRole()은 시스템상에서 특정 권한을 가진 사람만이 접근할 수 있음
                 .antMatchers("/manager/**").access(
                         "hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                 .antMatchers("/admin/**").access(
                         "hasRole('ROLE_ADMIN')")
                 .anyRequest().permitAll()
                 .and()
                 .formLogin()
                 .loginPage("/loginForm");

         return http.build();

    }
}
