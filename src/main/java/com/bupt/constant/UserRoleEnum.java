package com.bupt.constant;

public enum UserRoleEnum {
    /**
     * 普通用户
     */
    ORDINARY(0, "ROLE_ORDINARY"),
    /**
     * 管理员
     */
    ADMIN(1,"ROLE_ADMIN");

    public final Integer code;
    public final String name;

    UserRoleEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserRoleEnum getByCode(Integer code) {
        for (UserRoleEnum roleEnum : UserRoleEnum.values()) {
            if (roleEnum.code.equals(code)) {
                return roleEnum;
            }
        }
        return null;
    }
}
