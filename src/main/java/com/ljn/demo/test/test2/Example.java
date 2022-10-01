package com.ljn.demo.test.test2;

class  Subclass extends BaseClass {}

class BaseClass {
    String str;
    public BaseClass() {
        System.out.println("ok");
    }
    public BaseClass(String s) {
        str = s;
    }

}


