package com.ljn.demo.test;

import java.util.Scanner;

public class Demo03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        int count5 = 0;
        int count4 = 0;
        int count1 = 0;
        while (money > 8) {
            money -= 5;
            count5+=1;
        }
        if (money == 8) {
            count4 = 2;
            money = 0;
        }
        if (money >=5 ) {
            count5++;
            money -= 5;
        }
        if (money >= 4) {
            count4 = 1;
            count1 = money-4;
        } else {
            count1 = money;
        }
        System.out.println(count1+","+count4+","+count5);
    }
}
