package com.example.backend_rest.dto;

import lombok.*;

@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String token;
    private String userName;
    private String password;
    private String id;
}
