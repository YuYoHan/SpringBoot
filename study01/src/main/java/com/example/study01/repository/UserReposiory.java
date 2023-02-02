package com.example.study01.repository;

import com.example.study01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiory extends JpaRepository<User, Long> {
}
