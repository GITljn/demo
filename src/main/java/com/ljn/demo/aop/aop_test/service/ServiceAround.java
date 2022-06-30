package com.ljn.demo.aop.aop_test.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceAround {
    public void deleteAll(){
        System.out.println("ServiceAround中deleteAll的核心代码");
    }
}
