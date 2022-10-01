package com.ljn.demo.test.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        int ans = 0;
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Helper helper = new Helper(n);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n-1; i++) {
            helper.m(i+1, sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            map.put(helper.f(i+1), map.getOrDefault(helper.f(i+1), 0)+1);
        }
        for (Integer value : map.values()) {
            ans = ans + value / 2;
        }
        System.out.println(ans);
    }

    static class Helper {
        int p[];
        public Helper(int n) {
            this.p = new int[n+1];
            for (int i = 0; i < p.length; i++) {
                p[i] = i;
            }
        }

        public int f(int x) {
            return  x == p[x]?x:(p[x]= f(p[x]));
        }

        public void m(int x, int y) {
            int i = f(x);
            int j = f(y);
            if (i != j) {
                p[i] = j;
            }
        }
    }
}
