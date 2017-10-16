package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/25.
 */
public class PowOfN {

    @Test
    public void test(){
        Assert.assertTrue(myPow2(2, 6)==64);
        Assert.assertTrue(myPow2(3, 6)==27*27);
        Assert.assertTrue(myPow2(3, 9)==27*27*27);
        Assert.assertTrue(myPow2(10, -1)==0.1);
        Assert.assertTrue(myPow2(34.00515, 3)==34.00515 * 34.00515 * 34.00515);
        Assert.assertTrue(myPow2(34.00515, -3)==1/myPow2(34.00515, 3));

        long start = System.currentTimeMillis();
        System.out.println(myPow2(0.00001, 2147483647));
        System.out.println("cost : " + (System.currentTimeMillis()-start));
    }


    /**
     * Accepted
     * @param x
     * @param n
     * @return
     */
    public double myPow2(double x, int n){
        if (n==1){
            return x;
        }
        if (n==0){
            return 1;
        }
        if (n==-1){
            return 1/x;
        }
        double temp = myPow2(x, n/2);

        if (n%2==0){
            return temp * temp;
        }
        if (n%2==1){
            return  x * temp * temp;
        }
        if (n%2==-1){
            return   temp * temp /x;
        }
        return 0;
    }


    /**
     * time limit exceed
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n){
        if (n==0){
            return 1;
        }
        if (n==-1){
            return 1/x;
        }
        if (n==1){
            return x;
        }
        int half = n/2;
        double left = myPow(x, half);
        double right = myPow(x, n-half);
        return left * right;
    }




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

    /**
     * Accepted
     * @param n
     * @return
     */
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
