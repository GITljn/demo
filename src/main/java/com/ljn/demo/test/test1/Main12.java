package com.ljn.demo.test.test1;

import java.util.Arrays;
import java.util.Scanner;

public class Main12 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String str = sc.nextLine();
        boolean[] flag = new boolean[26];
        flag['r'-'a'] = true;
        flag['g'-'a'] = true;
        flag['b'-'a'] = true;
        boolean[] lF = new boolean[26];
        boolean[] rF = new boolean[26];
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (c == 'r' || c == 'g' || c == 'b') {
                lF[c-'a'] = true;
                if (Arrays.equals(flag, lF)) {
                    break;
                }
            }
            i++;
        }
        int j = str.length()-1;
        while (j >= 0) {
            char c = str.charAt(j);
            if (c == 'r' || c == 'g' || c == 'b') {
                rF[c-'a'] = true;
                if (Arrays.equals(flag, rF)) {
                    break;
                }
            }
            j--;
        }
        if (j <= i) {
            System.out.println(0);
        } else {
            System.out.println(j-i);
        }
    }
}
