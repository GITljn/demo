package com.ljn.demo.dynamic_proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyClass2 implements MethodInterceptor {
    /**
     * 代理类
     * @param o            目标类对象
     * @param method       拦截的方法
     * @param args         方法的参数
     * @param methodProxy  用于调用原始方法
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置处理方法");
        // invoke和invokeSuper的区别
        // method.invoke(o, args)和methodProxy.invoke(o, args)的区别
        // Object result = methodProxy.invoke(o, args);    // 报错
        // Object result = method.invoke(o, args);         // 报错
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("后置处理方法");
        return result;
    }
}
