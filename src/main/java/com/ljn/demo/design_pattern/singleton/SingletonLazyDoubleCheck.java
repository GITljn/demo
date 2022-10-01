package com.ljn.demo.design_pattern.singleton;

public class SingletonLazyDoubleCheck {
    // volatile
    private volatile static SingletonLazyDoubleCheck singletonLazyDoubleCheck;

    private SingletonLazyDoubleCheck() {

    }

    public static SingletonLazyDoubleCheck getSingletonLazyDoubleCheck() {
        if (singletonLazyDoubleCheck == null) {
            synchronized (SingletonLazyDoubleCheck.class) {
                if (singletonLazyDoubleCheck == null) {
                    singletonLazyDoubleCheck = new SingletonLazyDoubleCheck();
                }
            }
        }
        return singletonLazyDoubleCheck;
    }
}
