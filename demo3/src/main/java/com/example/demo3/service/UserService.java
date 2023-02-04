package com.example.demo3.service;


import com.example.demo3.DTO.UserDTO;
import com.example.demo3.entity.User;
import com.example.demo3.repository.UserReposiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReposiory userReposiory;

    public void save(UserDTO user) {
        // 1. dto → entity 변환
        // 2. repository의 save 메서드 호출
        User userEmtity = User.toUserEmtity(user);
        userReposiory.save(userEmtity);

        // repository의 save메서드 호출 (조건: entity 객체를 넘겨줘야 함)

    }
}
