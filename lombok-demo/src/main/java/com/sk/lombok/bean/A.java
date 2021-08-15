package com.aspirecn.lombok.bean;

public class A {
    public static void main(String[] args) {
        System.out.println(printAllKeys(4));
    }
    public static int printAllKeys(int n) {
        if(n == 1){
          return 1;
        }

        if(n == 2){
            return 2;
        }

        return printAllKeys(n-1) + printAllKeys(n - 2);
    }
}
