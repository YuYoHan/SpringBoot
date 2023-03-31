package com.example.board2.controller;

import com.example.board2.domain.UserDTO;
import com.example.board2.service.UserSerivce;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@AllArgsConstructor
@NoArgsConstructor
public class UserController {

    private UserSerivce userSerivce;

    // 모든 회원 정보를 가져오는 API
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUser() {
        List<UserDTO> userDTOList = userSerivce.getAllUser();
        return ResponseEntity.status(HttpStatus.OK).body(userDTOList);
    }

    // 회원 정보를 가져오는 API
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        UserDTO userDTO = userSerivce.getUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userDTO);
    }


    /**
     * 회원 가입 API
     * @return ResponseEntity<UserResponse> 201 Created, 가입된 회원의 정보
     */
    @PostMapping("/")
    public ResponseEntity<Boolean> signUp(@RequestBody UserDTO userDTO) {
        boolean login = userSerivce.join(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(login);
    }

    // 로그인
    @PostMapping("/")
    public String login(@PathVariable String userId, @PathVariable String password) {
        UserDTO loginUser = userSerivce.login(userId, password);
        if(loginUser != null) {
            return "로그인이 성공했습니다.";
        }
        return "아이디가 없습니다.";
    }

    // 회원 정보 수정
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO user = userSerivce.update(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    // 회원 탈퇴(삭제) API
    // 204 : NO_CONTENT
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userSerivce.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }





}
