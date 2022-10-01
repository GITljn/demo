package com.ljn.demo.design_pattern.singleton;

public class SingletonHungry {
    private static SingletonHungry singletonHungry = new SingletonHungry();
    // private
    private SingletonHungry() {

    }
    // static
    public static SingletonHungry getSingletonHungry() {
        return singletonHungry;
    }
}
