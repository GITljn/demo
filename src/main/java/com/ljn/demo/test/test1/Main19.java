package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main19 {
    public static void main(String[] args) {
        int ans = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n];
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            p[i] = sc.nextInt();
        }
        boolean[] used = new boolean[n+1];
        int i=0,j=0;
        while (i<n){
            if (used[s[j]]) {
                j++;
            } else {
                used[p[i]] = true;
                if(s[j] != p[i]) {
                    i++;
                    ans++;
                } else {
                    j++;
                    i++;
                }
            }
        }
        System.out.println(ans);
    }
}
