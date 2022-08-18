package com.ljn.demo.test;

public class Person {
    String name;
    String nickName;
    public Person(){}

    public Person(String name, String nickName) {
        this.name = name;
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public String getNickName() {
        return nickName;
    }
}
