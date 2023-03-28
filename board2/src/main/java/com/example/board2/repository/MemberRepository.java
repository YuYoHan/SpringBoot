package com.example.board2.repository;

import com.example.board2.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickName(String nickName);

    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);
}
