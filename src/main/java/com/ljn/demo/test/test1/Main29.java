package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] l = new int[n];
        int[] r = new int[n];
        for (int i = 0; i < n; i++) {
            l[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            r[i] = sc.nextInt();
        }
        int[] t = new int[n];
        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
        }
        int[] temp = getBeatufulNum();
        for (int i = 0; i < n; i++) {
            int res = 0;
            for (int j = l[i]; j <= r[i]; j++) {
                if (temp[j] == t[i]) {
                    res++;
                }
            }
            System.out.print(res+" ");
        }
    }
    public static int[] getBeatufulNum() {
        int[] res = new int[700001];
        for (int i = 0; i <= 700000; i++) {
            res[i] = getBeatufulNum(i);
        }
        return res;
    }

    public static int getBeatufulNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int res = Integer.parseInt(Character.toString(chars[0]));
        for (int i = 1; i < chars.length; i++) {
            res ^= Integer.parseInt(Character.toString(chars[i]));
        }
        return res;
    }
    
}
