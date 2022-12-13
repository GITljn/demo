package com.ljn.demo.response_bean.me2;


import lombok.Data;

// 不加Data注解无法转换成json
@Data
public class R <T> {
    // 成功的code可能不同，success可以确定是否成功
    private Boolean success;
    private Integer code;
    private String msg;
    private T data;

    private R(){}

    public static R success() {
        R r = new R();
        r.success = true;
        r.code = REnum.SUCCESS.getCode();
        r.msg = REnum.SUCCESS.getMessage();
        return r;
    }

    public static R error() {
        R r = new R();
        r.success = false;
        r.code = REnum.ERROR.getCode();
        r.msg = REnum.ERROR.getMessage();
        return r;
    }

    public R codeAndMsg(REnum rEnum) {
        this.code = rEnum.getCode();
        this.msg = rEnum.getMessage();
        return this;
    }

    public R code(Integer code) {
        this.code = code;
        return this;
    }

    public R msg(String msg) {
        this.msg = msg;
        return this;
    }

    public R data(T data) {
        this.data = data;
        return this;
    }
}
