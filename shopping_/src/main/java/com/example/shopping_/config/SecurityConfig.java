package com.example.shopping_.config;

import com.example.shopping_.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
*   해당 어노테이션을 선언하여 해당 클래스가 Spring Container에
*   관리가 되는 설정 클래스임을 선언하며 Bean을 구성할 클래스임을
*   알리는 어노테이션을 의미합니다.
* */
@Configuration
// WebSecurityConfigureAdapter를 상속받는 클래스에
// @EnableWebSecurity 어노테이션을 선언하면 SpringSecurityFilterChain이 자동으로 포함됩니다.
// WebSecurityConfigureAdapter를 상속받아서 메소드 오바라이딩을 통해 보안 설정을
// 커스터마이징할 수 있습니다.
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    MemberService memberService;

    // 해당 메서드 내에서 CustomAuthenticationFilter 호출합니다.
    // HTTP에 대해서 ‘인증’과 ‘인가’를 담당하는 메서드이며
    // 필터를 통해 인증 방식과 인증 절차에 대해서 등록하며 설정을 담당하는 메서드이다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                // 로그인 페이지 URL을 설정
                .loginPage("/member/login")
                // 로그인 성공 시 이동할 URL을 설정
                .defaultSuccessUrl("/")
                // 로그인 시 사용할 파라미터 이름으로 email을 지정
                .usernameParameter("email")
                // 로그인 실패시 이동할 URL 설정
                .failureUrl("/member/login/error")
                .and()
                .logout()
                // 로그아웃 URL을 설정
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                // 로그아웃 성공 시 이동할 URL 설정
                .logoutSuccessUrl("/");
        return http.build();
    }

    // 정적 자원(Resource)에 대해서 인증된 사용자가 정적 자원에 대한
    // 접근에 대해 ‘인가’에 대한 설정을 담당하는 메서드이다.
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/ignore1", "/ignore2");
    }

    // 비밀번호를 데이터베이스에 그대로 저장했을 경우, 데이터베이스가 해킹당하면
    // 고객의 회원 정보가 그대로 노출 됩니다. 이를 해결하기 위해서
    // BCryptPasswordEncoder의 해시 함수를 이용하여 비밀번호를
    // 암호화하여 저장합니다. BCryptPasswordEncoder를 빈으로 등록하여 사용하겠습니다.
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 비밀번호를 암호화하기 위한 BCrypt 인코딩을 통하여 비밀번호에 대한 암호화를 수행합니다.
        return new BCryptPasswordEncoder();
    }


    // 스프링 시큐리티에서 인증은 AuthenticationManager를 통해 이루어지며
    // AuthenticationManagerBuilder가 AuthenticationManager를 생성합니다.
    // userDetailService를 구현하고 있는 객체로 memberService를 지정하주며
    // 비밀번호 암호화를 위해서 passwordEncoder를 지정해준다.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationManagerBuilder auth) throws Exception {
        return  auth.userDetailsService(memberService)
                .passwordEncoder(passwordEncoder()).and().build();
    }
}
