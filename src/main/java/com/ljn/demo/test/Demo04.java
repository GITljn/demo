package com.ljn.demo.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Demo04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] strs = str.split(";");
        int price = Integer.parseInt(strs[1]);
        String[] moneysStr = strs[0].split(",");
        Integer[] moneys = new Integer[moneysStr.length];
        for (int i = 0; i < moneysStr.length; i++) {
            moneys[i] = Integer.parseInt(moneysStr[i]);
        }
        Arrays.sort(moneys, Collections.reverseOrder());
        System.out.println();
    }
}
