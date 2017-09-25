package com.leishb.leetcode.math;

/**
 * Created by me on 2017/9/25.
 */
public class PowOfTwo {


    public boolean isPowOfTwo2(int n){
        return (n & (n-1)) == 0;
    }


    public boolean isPowOfTwo(int n){
        if (n <=0){
            return false;
        }
        while (n!=1){
            if (n%2==1){
                return false;
            }
            n>>=1;
        }
        return true;
    }
}
