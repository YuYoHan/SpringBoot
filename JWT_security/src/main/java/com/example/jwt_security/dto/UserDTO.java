package com.example.jwt_security.dto;

import com.example.jwt_security.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotNull
    @Size(min = 3, max=50)
    private  String  userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max=100)
    private String userPw;

    @NotNull
    @Size(min = 3, max=50)
    private String nickName;

    private Set<AuthorityDTO> authorityDtoSet;

    public static UserDTO from(User user) {
        if(user == null) return null;

        return UserDTO.builder()
                .userName(user.getUsername())
                .nickName(user.getNickname())
                .authorityDtoSet(user.getAuthorities().stream()
                        .map(authority -> AuthorityDTO.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
