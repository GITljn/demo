package com.ljn.demo.dynamic_proxy.jdk;

import java.lang.reflect.Proxy;

public class ProxyClassObjectFactory {
    public static Object getProxyClassObject(Object target) {
        Object proxyClassObject = Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new ProxyClass(target));
        return proxyClassObject;
    }
}
