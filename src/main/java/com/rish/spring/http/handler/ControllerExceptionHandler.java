package com.rish.spring.http.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice(basePackages = "com.rish.spring.http.controller")
public class ControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, HttpServletRequest request) {
        log.error("Failed to return response", e);
        return "error/error500";
    }

}
