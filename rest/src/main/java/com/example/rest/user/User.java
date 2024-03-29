package com.example.rest.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    @Size(min=2, message = "name은 2글자 이상 써주세요")
    private String name;
    @Past
    private LocalDateTime joinDate;
}
