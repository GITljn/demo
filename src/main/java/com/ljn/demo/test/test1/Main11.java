package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int res = 0;
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            int min = Math.min(Math.min(a, b), c);
            res += min * 2;
            b -= min;
            if (b > 1) {
                res += b-1;
            }
            System.out.println(res);
        }

    }
}
