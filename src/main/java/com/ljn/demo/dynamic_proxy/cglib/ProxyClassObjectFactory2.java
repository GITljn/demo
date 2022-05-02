package com.ljn.demo.dynamic_proxy.cglib;

import net.sf.cglib.proxy.Enhancer;

public class ProxyClassObjectFactory2 {
    public static Object getProxyClassObject(Class<?> clas) {
        // 相当于JDK动态代理中的Proxy
        Enhancer enhancer = new Enhancer();
        // 相当于target.getClass().getClassLoader
        enhancer.setClassLoader(clas.getClassLoader());
        // 相当于target.getClass().getInterfaces
        enhancer.setSuperclass(clas);
        // 相当于new ProxyClass
        enhancer.setCallback(new ProxyClass2());
        return enhancer.create();
    }
}
