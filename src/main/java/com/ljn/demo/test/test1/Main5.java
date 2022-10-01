package com.ljn.demo.test.test1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main5 {

    public static void main(String[] args) {
        String[] words = {"This", "is", "an", "instance", "of", "code", "alignment."};
        System.out.println(justifyFill(words, 16));
    }

    public static String helper2(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(" ");
            num--;
        }
        return sb.toString();
    }

    public static String helper1(String[] w, int l, int r, int length, int L) {
        StringBuffer sb = new StringBuffer();
        if (r != w.length-1) {
            int mod = 0;
            int div = 0;
            int bN = L - length;
            int gN = r - l;
            if (gN != 0) {
                mod = bN % gN;
                div = bN / gN;
            }
            for (int i = l; i <= r; i++) {
                sb.append(w[i]);
                if (i < r) {
                    if (i-l >= mod) {
                        sb.append(helper2(div));
                    } else {
                        sb.append(helper2(div+1));
                    }
                }
            }
        } else {
            for (int i = l; i <= r; i++) {
                sb.append(w[i]);
                if (i < r) {
                    sb.append(" ");
                }
            }

        }
        while (L != sb.length()) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public static ArrayList<String> justifyFill (String[] words, int L) {
        // write code here
        int l = 0, r, length = 0;
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            if (L < length+i-l+words[i].length()) {
                r = i - 1;
                result.add(helper1(words, l, r, length, L));
                length = words[i].length();
                l = i;
            } else {
                length += words[i].length();
            }
        }
        result.add(helper1(words, l, words.length-1, length, L));
        return result;
    }

    public int playNum (int n, int m) {
        // write code here
        boolean[] arr = new boolean[n];
        int exit = 0;
        int count = 0;
        int i = 0;
        while (exit < n - 1) {
            if (!arr[i]) {
                count++;
                if (count == m) {
                    arr[i] = true;
                    exit++;
                }
            }
            i++;
            i %= n;
        }
        for (int j = 0; j < n; j++) {
            if (!arr[j]) {
                return j+1;
            }
        }
        return 0;
    }
}
