package com.example.demo3.DTO;

import com.example.demo3.entity.User;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String userEmail;
    private String userPw;
    private String userName;

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setUserEmail(user.getUserEmail());
        userDTO.setUserPw(user.getUserPw());
        userDTO.setUserName(user.getUserName());
        return userDTO;
    }
}
