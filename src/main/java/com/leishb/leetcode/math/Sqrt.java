package com.leishb.leetcode.math;

/**
 * Created by me on 2017/9/18.
 */
public class Sqrt {


    public static void main(String[] args) {
        Sqrt sqrt = new Sqrt();
        System.out.println(sqrt.sqrt(17));
        System.out.println(sqrt.sqrt(18));
        System.out.println(sqrt.sqrt(19));
        System.out.println(sqrt.sqrt(12));
        System.out.println(sqrt.sqrt(26));
        System.out.println(sqrt.sqrt(4));
        System.out.println(sqrt.sqrt(225));
        System.out.println(sqrt.sqrt(1024));
        System.out.println(sqrt.sqrt(1025));
        System.out.println(sqrt.sqrt(1888));

        System.out.println(sqrt.sqrt(2147395599));
    }


    public int sqrt(int x){
       if (x==0||x==1){
           return x;
       }
        if (x < 0) {
            return 0;
        }
        int left = 0;
        int right = x;
        for (int i = left;i<=right;i++){
            //attention 不是完全平方数的情况
            if (left+1==right){
                return left;
            }
            int half = (left+right)/2;
            //防止溢出，不使用乘法
            if (x/half == half){
                return half;
            }
            if (half > x/half){
                right = half;
            }
            if (half < x/half){
                left = half;
            }
        }
        return 0;
    }
}
