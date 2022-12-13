package com.ljn.demo.response_bean.guli;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class R {
    // 成功的code可能不同，success可以确定是否成功
    private Boolean success;
    private Integer code;
    private String msg;
    private Map<String, Object> data = new HashMap<>();

    private R(){}

    public static R success() {
        R r = new R();
        r.success = true;
        r.code = ResCode.SUCCESS;
        r.msg = "成功";
        return r;
    }

    public static R error() {
        R r = new R();
        r.success = false;
        r.code = ResCode.ERROR;
        r.msg = "失败";
        return r;
    }

    public R msg(String message) {
        this.msg = message;
        return this;
    }

    public R code(Integer code) {
        this.code = code;
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
