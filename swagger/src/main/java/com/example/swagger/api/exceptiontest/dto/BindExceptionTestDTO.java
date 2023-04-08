package com.example.swagger.api.exceptiontest.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class BindExceptionTestDTO {
    @NotBlank(message = "해당 값은 필 수 입력값입니다")
    private String value1;

    @Max(value = 10, message = "최대 입력값은 10입니다.")
    private Integer value2;
}
