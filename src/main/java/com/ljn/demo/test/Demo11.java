package com.ljn.demo.test;

import java.util.Arrays;
import java.util.Scanner;

public class Demo11 {
    public static int getLen(String a, String b) {
        int res = 0, eps = 1, s1 = 0, s2 = 0;
        String[] st1 = a.split(":");
        String[] st2 = b.split(":");
//        for (int i = 0; i < 3; i++) {
//            System.out.println(st1[i]+" " + st2[i]);
//        }
        for (int i = 2; i >= 0; i--) {
            s1 += Integer.parseInt(st1[i])*eps;
            s2 += Integer.parseInt(st2[i])*eps;
            eps *= 60;
        }
        return s1-s2;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String st = sc.nextLine();
        String[] str = st.split(" ");
        Arrays.sort(str);
        int maxLen = 0, curId=0;
        for(int i=1; i < str.length; i++) {
            int tempLen = getLen(str[i], str[i-1]);
            if (maxLen < tempLen) {
                maxLen = tempLen;
                curId = i-1;
            }
        }
        int tempLen = getLen(str[0], str[str.length-1])+3600*24;
        if (tempLen > maxLen) {
            curId = n-1;
        }
        System.out.println(str[curId]);
    }
}
