package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main38 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(get_lcm(a, b));
    }
    public static int get_gcd(int a, int b) {
        while (b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
//        int max, min;
//        max = Math.max(a, b);
//        min = Math.min(a, b);
//        if (max % min != 0) {
//            return get_gcd(min, max % min);
//        } else {
//            return min;
//        }
    }
    public static int get_lcm(int a, int b) {
        if (a > b)
            return a * b / get_gcd(a, b);
        else
            return a * b / get_gcd(b, a);
    }
}
