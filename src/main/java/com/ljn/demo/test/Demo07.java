package com.ljn.demo.test;

import java.util.Scanner;

public class Demo07 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String orign = scanner.next();
        String target = scanner.next();
        for (int subLen = target.length(); subLen <= orign.length(); subLen++) {
            for (int i = 0; i+subLen <= orign.length(); i++) {
                if (containsSub(orign.substring(i, i+subLen), target)) {
                    System.out.println(orign.substring(i, i+subLen));
                    return;
                }
            }
        }
    }

    private static boolean containsSub(String substring, String target) {
        for (int i = 0; i < target.length(); i++) {
            if (!substring.contains(target.charAt(i)+"")) {
                return false;
            }
        }
        return true;
    }
}
