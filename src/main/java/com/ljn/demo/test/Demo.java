package com.ljn.demo.test;

import java.util.Stack;

public class Demo {
    public static void main(String[] args) {
        Integer num1 = new Integer(1);
        Integer num2 = num1;
        doSomething(num2);
        System.out.println(num1);
        System.out.println(num1 == num2);
        String s = "2[3[3[a]]5[b]]";
        Stack<Integer> num = new Stack<>();
        Stack<String> str = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)>='0' && s.charAt(i)<='9') {
                num.push(Integer.valueOf(s.charAt(i)+""));
            } else if (s.charAt(i) == '[') {
                continue;
            } else if (s.charAt(i) == ']') {
                int count = num.pop();
                String string = str.pop();
                String temp = "";
                for (int j = 0; j < count; j++) {
                    temp += string;
                }
                str.push(temp);
            } else {
                str.push(s.charAt(i)+"");
            }
        }
        String res = "";
        for (String s1 : str) {
            res+=s1;
        }
        System.out.println(res);
    }
    public static void doSomething (Integer num) {
        num = new Integer(500);
    }
}
