package com.ljn.demo.test;

import java.util.Scanner;

public class Demo16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[n+1];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        boolean[] used = new boolean[n];
        int beginIndex = 0;
        int[] ans = new int[n];
        int index = 0;
        int times = 0;
        while(index < n) {
            if(!used[beginIndex]) {
                times++;
            }
            if (times == 3) {
                ans[beginIndex] = num[index];
                index++;
                times = 0;
                used[beginIndex] = true;
            }
            beginIndex = (beginIndex+1) % n;
        }
        for (int i: ans) {
            System.out.println(i+" ");
        }
    }
}
