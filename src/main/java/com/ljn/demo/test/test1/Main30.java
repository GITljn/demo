package com.ljn.demo.test.test1;

public class Main30 {
    public static void main(String[] args) {
        int encryptedNumber = 4296;
        int decryption = 1601;
        int number = 4757;
        System.out.println(Decrypt(encryptedNumber, decryption, number));
    }

    public static int Decrypt (int encryptedNumber, int decryption, int number) {
        // write code here
        long ans = 1;
        while (decryption > 0) {
            if ((decryption & 1) == 1) {
                ans = ans * encryptedNumber % number;
            }
            decryption /= 2;
            encryptedNumber = encryptedNumber * encryptedNumber % number;
        }
        return (int)ans;
    }
}
