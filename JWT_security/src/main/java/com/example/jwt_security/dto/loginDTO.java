package com.example.jwt_security.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class loginDTO {
    @NotNull
    @Size(min =3, max=50)
    private String userName;

    @NotNull
    @Size(min = 3, max = 50)
    private String userPw;
}
