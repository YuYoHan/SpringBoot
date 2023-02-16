package com.example.study02.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class UserDTO {
    private String id;
    // json에서 제외
    @JsonIgnore
    private String password;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING) // ISO-8601 형식으로 변환
    private LocalDateTime registerDateTime;

    @JsonFormat(pattern = "yyyyMMddHHmmss")
    private LocalDateTime updateTime;
}
