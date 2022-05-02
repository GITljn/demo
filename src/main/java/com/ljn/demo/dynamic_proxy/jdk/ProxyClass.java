package com.ljn.demo.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyClass implements InvocationHandler {
    private final Object target;

    public ProxyClass(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置处理方法");
        // 通过反射的方式调用方法
        Object result = method.invoke(target, args);
        System.out.println("后置处理方法");
        return result;
    }
}
