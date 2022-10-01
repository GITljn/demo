package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int q = sc.nextInt();
        for (int i = 0; i < q; i++) {
            int num = sc.nextInt();
            if (num % 2 == 0) {
                System.out.println(num);
                continue;
            }
            char[] chars = Integer.toString(num).toCharArray();
            int index = -1;
            int j = 0;
            while (j < chars.length) {
                if (Integer.parseInt(Character.toString(chars[j])) % 2 == 0) {
                    index = j;
                    break;
                }
                j++;
            }
            if (index == -1) {
                System.out.println(-1);
                continue;
            }
            char temp = chars[index];
            chars[index] = chars[chars.length-1];
            chars[chars.length-1] = temp;
            System.out.println(Integer.parseInt(new String(chars)));
        }
    }
}
