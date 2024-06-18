package com.example.democommon.handler;

import com.example.democommon.util.ServerResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ServerResponse<String> exceptionHandler(Exception exception){
        return ServerResponse.createByError();
    }
}
