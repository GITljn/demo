package com.ljn.demo.test.test1;

import java.util.Scanner;

public class Main25 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        str = str.toLowerCase();
        if (new StringBuilder(str).reverse().toString().equals(str)) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
