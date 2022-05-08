package com.ljn.demo.security.common;

public enum REnum {
    // 通用
    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "注销成功"),
    LOGOUT_ERROR(200, "注销失败"),
    AUTHENTICATION_ERROR(401, "认证失败"),
    AUTHORITY_ERROR(403, "权限不足")
    ;
    private final Integer code;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    REnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
