package com.example.jwt_security.service;

import java.util.Collections;
import java.util.Optional;

import com.example.jwt_security.dto.UserDTO;
import com.example.jwt_security.entity.Authority;
import com.example.jwt_security.entity.User;
import com.example.jwt_security.exception.DuplicateMemberException;
import com.example.jwt_security.exception.NotFoundMemberException;
import com.example.jwt_security.repository.UserRepository;
import com.example.jwt_security.util.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserDTO signup(UserDTO userDto) throws DuplicateMemberException {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUserName()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getUserPw()))
                .nickname(userDto.getNickName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return UserDTO.from(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public UserDTO getUserWithAuthorities(String username) {
        return UserDTO.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    @Transactional(readOnly = true)
    public UserDTO getMyUserWithAuthorities() {
        return UserDTO.from(
                SecurityUtil.getCurrentUserName()
                        .flatMap(userRepository::findOneWithAuthoritiesByUsername)
                        .orElseThrow()
        );
    }
}
