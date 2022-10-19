package com.ljn.demo.spring_security.controller;

import com.ljn.demo.spring_security.utils.R;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
//    @PreAuthorize("hasAuthority('sys:dept:list')")
    // 利用自定义的验证方式
    @PreAuthorize("@securityExpression.hasAuthority('sys:dept:list')")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/hi")
    @PreAuthorize("hasAuthority('sys:dept:list111')")
    public String hi() {
        return "hi";
    }

    @RequestMapping("/testCors")
    public R testCors() {
        return R.success().code(200).msg("testCors");
    }
}
