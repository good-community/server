package com.bupt.controller;

import com.bupt.constant.RetCodeEnum;
import com.bupt.model.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(AuthenticationException.class)
    public Response handleAuthenticationException(AuthenticationException e) {
        log.warn("login error, {}", e.getMessage());
        return Response.any(RetCodeEnum.NOT_LOGIN, e.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    public Response handleUnauthorizedException(UnauthorizedException e) {
        log.warn("not role, {}", e.getMessage());
        return Response.any(RetCodeEnum.NOT_ROLE, e.getMessage());
    }

    /**
     * validation异常
     */
    @ExceptionHandler(BindException.class)
    public Response handleBindException(BindException e) {
        List<FieldError> allErrors = e.getBindingResult().getFieldErrors();
        String msg = allErrors.stream()
                .map(err -> String.format("%s = %s, %s",
                        err.getField(),
                        err.getRejectedValue(),
                        err.getDefaultMessage()))
                .collect(Collectors.joining(";"));
        log.warn("validation error, {}", msg);
        return Response.any(RetCodeEnum.WRONG_PARAM, msg);
    }


    @ExceptionHandler(Exception.class)
    public Response handleOthers(Exception e) {
        log.warn("error", e);
        return Response.err(e);
    }
}
