package com.ljn.demo.test;

public class Mmm {
    private static Mmm mmm = new Mmm();
    public static  int a;
    public static int b = 0;
    private Mmm() {
        a = 5;
        b = 5;
    }
    public static  Mmm getInstance() {
        return mmm;
    }
}
