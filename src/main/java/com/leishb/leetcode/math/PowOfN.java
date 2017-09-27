package com.leishb.leetcode.math;

/**
 * Created by me on 2017/9/25.
 */
public class PowOfN {


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

    public boolean powOfThree2(int n){
        // 1162261467 is 3^19,  3^20 is bigger than int
        return ( n>0 &&  1162261467%n==0);
    }

    public boolean powOfThree(int n){
        if (n<=0){
            return false;
        }
        while (n!=1){
            if (n%3!=0){
                return false;
            }
            n/=3;
        }
        return n==1;
    }
}
