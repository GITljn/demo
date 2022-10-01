package com.ljn.demo.test.test1;

import java.util.Scanner;

/**
 * 烽火1
 * 题目：现在给出三个数字a，b和c，你可以在保证a+b不变的情况下对两数进行调整，设调整以后是a’和b’，请问在使得a’/c+b’/c(均为整数除法)最大的情况下，最小的|a’-a|+|b’-b|是多少。
 * 输入：输入仅包含三个正整数,a,b和c,中间用空格隔开。(1<=a,b,c<=10^18)
 * 输出：输出仅包含一行，即最小的|a’-a|+|b’-b|值。
 * 样例输入：5 8 3
 * 样例输出：2
 */
public class Main22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Long a = scanner.nextLong();
        Long b = scanner.nextLong();
        Long c = scanner.nextLong();
        Long r = b % c;
        Long l = a % c;
        if (c > r + l) {
            System.out.println(0);
            return;
        }
        Long res = Math.min(c - r, c - l) * 2;
        System.out.println(res);
    }
}
