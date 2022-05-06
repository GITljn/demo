package com.ljn.demo.response_bean.seckill;

import lombok.Data;

@Data
public class R {
    private Integer code;
    private String msg;
    private Object data;

    private R(){}

    public static R success() {
        R r = new R();
        r.code = REnum.SUCCESS.getCode();
        r.msg = REnum.SUCCESS.getMessage();
        r.data = null;
        return r;
    }

    public static R success(REnum rEnum) {
        R r = new R();
        r.code = rEnum.getCode();
        r.msg = rEnum.getMessage();
        r.data = null;
        return r;
    }

    public static R success(REnum rEnum, Object data) {
        R r = new R();
        r.code = rEnum.getCode();
        r.msg = rEnum.getMessage();
        r.data = data;
        return r;
    }

    public static R error() {
        R r = new R();
        r.code = REnum.ERROR.getCode();
        r.msg = REnum.ERROR.getMessage();
        r.data = null;
        return r;
    }

    public static R error(REnum rEnum) {
        R r = new R();
        r.code = rEnum.getCode();
        r.msg = rEnum.getMessage();
        r.data = null;
        return r;
    }

    public static R error(REnum rEnum, Object data) {
        R r = new R();
        r.code = rEnum.getCode();
        r.msg = rEnum.getMessage();
        r.data = data;
        return r;
    }
}
