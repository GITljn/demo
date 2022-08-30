package com.ljn.demo.aop.aop_test.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceExecution {
    public void deleteAll(){
        System.out.println("ServiceExecution中deleteAll的核心代码");
    }
}