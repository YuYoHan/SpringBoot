package com.example.demo3.repository;

import com.example.demo3.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposiory extends JpaRepository<User, Long> {
    // 이메일로 회원 정보 조회 (select * from user where user_email=?)
    Optional<User> findByUserEmail(String userEmail);
}
