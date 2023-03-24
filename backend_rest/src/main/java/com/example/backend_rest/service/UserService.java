package com.example.backend_rest.service;

import com.example.backend_rest.model.UserEntity;
import com.example.backend_rest.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {
        if(userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final  String userName = userEntity.getUsername();
        if(userRepository.existsByUsername(userName)) {
            log.warn("UserName already exists {}", userName);
            throw new RuntimeException("UserName already exists");
        }
        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String userName, final String password) {
        return userRepository.findByUsernameAndPassword(userName, password);
    }
}
