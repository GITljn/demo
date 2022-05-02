package com.ljn.demo.dynamic_proxy.jdk;

public class TargetClass implements TargetClassInterface {
    @Override
    public void printName(String name) {
        System.out.println("name: "+name);
    }
}
