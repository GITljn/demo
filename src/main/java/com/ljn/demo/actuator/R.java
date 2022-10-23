package com.ljn.demo.actuator;


import lombok.Data;

import java.util.HashMap;
import java.util.Map;

// 不加Data注解无法转换成json
@Data
public class R {
    private Boolean success;
    private Integer code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

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

    public R data(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }
}
