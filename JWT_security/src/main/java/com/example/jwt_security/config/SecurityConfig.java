package com.example.jwt_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

// 기본적인 web 보안을 활겅화 하겠다는 으미
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // HttpServletRequest를 사용하는 요청들에 대한 접근제한을 설정
                .authorizeRequests()
                // 나머지 요청들은 모두 인증되어야 한다는 의미
                .anyRequest().authenticated();

        http
                .formLogin()
                // /api/hello에 대한 요청은 인증없이 접근을 허용하겠다는 의미
                .loginPage("/api/hello").permitAll();

        return http.build();
    }

//    @Bean
//    public void configure(WebSecurity web) throws Exception {
//        web
//                .ignoring()
//                .antMatchers(
//                        "/h2-console/**",
//                        "/favicon.ico"
//                );
//    }
    // ↑ 이게 바뀌기 전 ↓ 바뀐 후

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // /h2-console/하위 모든 요청과 파비콘은 모두 무시하는 것으로 설정
        return (web) -> web.ignoring().antMatchers("/h2-console/**", "/favicon.ico");
    }

}
