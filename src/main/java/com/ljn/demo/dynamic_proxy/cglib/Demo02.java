package com.ljn.demo.dynamic_proxy.cglib;

public class Demo02 {
    public static void main(String[] args) {
        TargetClass2 target = (TargetClass2) ProxyClassObjectFactory2.getProxyClassObject(TargetClass2.class);
        target.printName("ljn");
    }
}
