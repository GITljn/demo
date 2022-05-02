package com.ljn.demo.dynamic_proxy.cglib;

import com.ljn.demo.dynamic_proxy.jdk.TargetClassInterface;

public class TargetClass2 implements TargetClassInterface {
    @Override
    public void printName(String name) {
        System.out.println("name: "+name);
    }
}
