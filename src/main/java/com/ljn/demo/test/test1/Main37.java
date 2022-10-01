package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main37 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(sc.next());
        int res = 0;
        if (sb.length() == 1) {
            res = 0;
        } else {
            while (true) {
                String temp = mul(sb.toString());
                if (temp.length() != 1) {
                    res++;
                    sb.delete(0, sb.length());
                    sb.append(temp);
                } else {
                    res++;
                    break;
                }
            }
        }
        System.out.println(res);
    }

    public static String mul(String str) {
        int res = 1;
        char[] ch = str.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            res *= (int) (ch[i]-'0');
        }
        return String.valueOf(res);
    }
}
