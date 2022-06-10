package com.ljn.demo.dynamic_proxy.jdk;

public class Demo {
    public static void main(String[] args) {
        // 必须是目标类接口，不能是目标类，因为得到的是目标类的父接口的实现类对象，不是目标类对象
        TargetClassInterface target = (TargetClassInterface)ProxyClassObjectFactory
                .getProxyClassObject(new TargetClass());
        target.printName("ljn");
    }
}
