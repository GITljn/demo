package com.ljn.demo.test;

import java.util.Arrays;
import java.util.Scanner;

public class Demo06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        int cnt = 1;
        char[] at = {'f', 'a', 'i', 'l', 'e', 'd'};
        int[] temp = new int[1000000];
        Arrays.fill(temp, 0);
        for (int i = 0; i < st.length(); i++) {
            int kId = 0, flag = 0;
            for(int j = 1; j <= 6; j++) {
                if (at[j-1] == st.charAt(i)) {
                    kId = j;
                    break;
                }
            }
            for (int j = 0; j < cnt; j++) {
                if (temp[j] + 1 == kId) {
                    temp[j]+=1;
                    if (temp[j] == 6) {
                        temp[j] = 0;
                    }
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) {
                if (kId == 1) {
                    temp[cnt++] = kId;
                } else {
                    cnt = -1;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}
