package com.ljn.demo.test;

import java.util.Arrays;
import java.util.Scanner;

public class Demo13 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int t = scanner.nextInt();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = scanner.nextInt();

        }
        Arrays.sort(times);
        int curTime = 0;
        int ret = 0;
        for (int value: times) {
            if (curTime+t <=value) {
                curTime+=t;
            } else {
                ret++;
            }
        }
        System.out.println(ret);
    }
}
