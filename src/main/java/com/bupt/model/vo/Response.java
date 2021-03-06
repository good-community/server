package com.bupt.model.vo;

import com.bupt.constant.RetCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;

/**
 * 统一响应封装
 */
@Data
@AllArgsConstructor
public class Response {
    private String code;
    private String msg;
    private Object data;

    public Response(RetCodeEnum retCodeEnum, Object data) {
        this.code = retCodeEnum.code;
        this.msg = retCodeEnum.msg;
        this.data = data;
    }

    public Response(RetCodeEnum retCodeEnum) {
        this.code = retCodeEnum.code;
        this.msg = retCodeEnum.msg;
    }

    public static Response ok() {
        return new Response(RetCodeEnum.SUCCESS);
    }

    public static Response ok(Object data) {
        return new Response(RetCodeEnum.SUCCESS, data);
    }

    public static Response err() {
        return new Response(RetCodeEnum.ERROR);
    }

    public static Response err(Object errDetail) {
        return new Response(RetCodeEnum.ERROR, errDetail);
    }

    public static Response err(String msg, Object errDetail) {
        return new Response(RetCodeEnum.ERROR.code, msg, errDetail);
    }

    public static Response fail(String msg) {
        return new Response(RetCodeEnum.FAIL.code, msg, null);
    }

    public static Response err(Exception e) {
        return new Response(RetCodeEnum.ERROR.code, e.getMessage(), Arrays.stream(e.getStackTrace()).limit(3));
    }

    public static Response any(RetCodeEnum codeEnum) {
        return new Response(codeEnum, null);
    }

    public static Response any(RetCodeEnum codeEnum, Object data) {
        return new Response(codeEnum, data);
    }
}
