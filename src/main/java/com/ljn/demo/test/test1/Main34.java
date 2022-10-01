package com.ljn.demo.test.test1;

public class Main34 {
    public static void main(String[] args) {

    }

    public String longestPrefix (String s) {
        // write code here
        for (int i = s.length(); i > 0; i--) {
            String pre = s.substring(0, i);
            String post = s.substring(s.length()-i);
            if (pre.equals(post)) {
                return pre;
            }
        }
        return null;
    }
}
