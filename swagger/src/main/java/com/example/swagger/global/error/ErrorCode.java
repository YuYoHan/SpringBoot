package com.example.swagger.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {;

    private HttpStatus httpStatus;
    private String error;
    private String message;

     ErrorCode(HttpStatus httpStatus, String error, String message) {
        this.httpStatus = httpStatus;
        this.error = error;
        this.message = message;
    }

    public String getErrorCode() {
        return "에러";
    }
}
