package com.example.backend_rest.persistence;

import com.example.backend_rest.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String userName);
    Boolean existsByUsername(String userName);
    UserEntity findByUsernameAndPassword(String userName, String password);
}
