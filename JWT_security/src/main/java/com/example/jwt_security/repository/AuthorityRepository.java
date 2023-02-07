package com.example.jwt_security.repository;

import com.example.jwt_security.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository  extends JpaRepository<Authority, String > {
}
