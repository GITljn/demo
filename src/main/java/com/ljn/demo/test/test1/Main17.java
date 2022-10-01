package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main17 {
    public static void main(String[] args) {

        for (int n = 0; n < 300; n++) {
            if (n == 1) {
                System.out.println(9);
                continue;
            }
            if (n == 2) {
                System.out.println(80);
                continue;
            }
            long res1 = (long) ((2L *n-3)*Math.pow(8, n-2) );
            long res2 = 0;
            int i = 3;
            while (i <= n) {
                res2 += amount((long) n, (long)(n-i)) *Math.pow(8, n-i)% 1000000007;
                i++;
            }
            res2 %= 1000000007;
            System.out.print((long) ((Math.pow(9, n)-(res1+res2)) % 1000000007)+",");
        }

    }

    public static double amount(double a, double b) {
        if (b==0) {
            return 1;
        }
        double da = 1;
        double xiao = 1;
        for (int i = 0; i < b; i++) {
            da = da * a;
            a--;
        }
        for (; b>0; b--) {
            xiao = xiao * b;
        }
        return da/xiao;
    }
}
