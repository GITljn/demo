package com.ljn.demo.actuator;

public enum REnum {
    // 通用
    SUCCESS(200, "成功"),
    ERROR(500, "失败"),
    // 登录
    LOGIN_ERROR(40001, "手机号或密码错误"),
    MOBILE_ERROR(40002, "手机号格式错误"),
    BIND_ERROR(40003, "参数校验错误"),
    SESSION_ERROR(40004, "登录状态已过期，请重新登录"),
    // 秒杀
    EMPTY_STOCK(50001, "库存不足"),
    REPEATE_BUY(50002, "该商品每人限购一件"),
    QUEUE(50003, "排队中")
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
