package com.ljn.demo.aop.aop_test.service;

import com.ljn.demo.aop.aop_test.aspect.InvokeLog;
import org.springframework.stereotype.Service;

@Service
public class ServiceAnnotation {

    @InvokeLog
    public void deleteAll(){
        System.out.println("ServiceAnnotation中deleteAll的核心代码");
    }
}
