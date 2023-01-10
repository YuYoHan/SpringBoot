package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BoardException.class)
    public String handleCustomException(BoardException exception, Model model) {
        model.addAttribute("exception", exception);
        return "/error/boardError";
    }

    @ExceptionHandler(Exception.class)
    public String handlerException(Exception exception, Model model) {
        model.addAttribute("exception", exception);
        return "/error/globalError";
    }

    // 404예외처리 핸들러
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException e, Model model){
        model.addAttribute("exception", e);
        return "/error/notfound";
    }


}
