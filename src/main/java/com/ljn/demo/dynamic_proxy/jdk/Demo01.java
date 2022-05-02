package com.ljn.demo.dynamic_proxy.jdk;

public class Demo01 {
    public static void main(String[] args) {
        // 必须是代理类接口，不能是代理类对象
        TargetClassInterface target = (TargetClassInterface)ProxyClassObjectFactory.getProxyClassObject(new TargetClass());
        target.printName("ljn");
    }
}
