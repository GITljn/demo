package com.ljn.demo.test.test1;

public class Main2 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String temp1 = "a";
        String temp2 = Character.toString((char) ('a'+1));
        sb.append(temp2);
        System.out.println(sb.toString());
    }
}
