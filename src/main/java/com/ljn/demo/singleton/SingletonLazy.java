package com.ljn.demo.singleton;

public class SingletonLazy {
    private static SingletonLazy singletonLazy;
    // private
    private SingletonLazy() {

    }
    // static
    public static synchronized SingletonLazy getSingletonLazy() {
        if (singletonLazy == null) {
            singletonLazy = new SingletonLazy();
        }
        return singletonLazy;
    }
}
