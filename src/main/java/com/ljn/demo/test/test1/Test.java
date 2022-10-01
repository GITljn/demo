package com.ljn.demo.test.test1;

public class Test {
 int m = 5;
 public void some(int x){
  m = x;
 }
    public static void main(String []args) {
  
       new Demo1().some(9);
    }
}
class Demo1 extends Test{
 int m = 8;
 public void some(int x){
  super.some(x);
  System.out.println(super.m);
  }
}