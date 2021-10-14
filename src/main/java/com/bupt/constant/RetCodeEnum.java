package com.bupt.constant;

/**
 * 业务返回码
 */
public enum RetCodeEnum {
    /**
     * 成功
     */
    SUCCESS("S0000", "处理成功"),
    /**
     * 异常（非预期）
     */
    ERROR("E0000", "发生异常"),
    /**
     * 操作失败（在预期内）
     */
    FAIL("F0000", "操作失败"),

    /**
     * 尚未登录
     */
    NOT_LOGIN("A0000", "尚未登录"),

    /**
     * 无角色权限
     */
    NOT_ROLE("A0001", "用户无角色权限"),
    /**
     * 参数错误
     */
    WRONG_PARAM("E0001", "参数错误");


    private final String code;
    private final String msg;

    RetCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
