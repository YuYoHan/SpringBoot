package com.example.demo3.service;


import com.example.demo3.DTO.UserDTO;
import com.example.demo3.entity.User;
import com.example.demo3.repository.UserReposiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReposiory userReposiory;

    public void save(UserDTO user) {
        // 1. dto → entity 변환
        // 2. repository의 save 메서드 호출
        User userEmtity = User.toUserEmtity(user);
        // repository의 save메서드 호출 (조건: entity 객체를 넘겨줘야 함)
        userReposiory.save(userEmtity);


    }

    public UserDTO login(UserDTO user) {
        /*
        *   1. 회원이 입력한 이메일로 DB에서 조회를 함
        *   2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        *
        * */
        Optional<User> byUserEmail = userReposiory.findByUserEmail(user.getUserEmail());
        if(byUserEmail.isPresent()) {
            // 조회 결과 있음
            User userEntity = byUserEmail.get();
            if(userEntity.getUserPw().equals(user.getUserPw())) {
                // 비밀번호 일치
                // entity → dto 변환
                UserDTO userDTO = UserDTO.toUserDTO(userEntity);
                return userDTO;
            } else {
                // 비밀번호 불일치
                return null;
            }
        } else {
            // 조회결과 없음
            return null;
        }
    }
}
