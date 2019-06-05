package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        Assert.assertTrue(myPow3(34.00515, -3, new HashMap<>())==1/myPow2(34.00515, 3));

        long start = System.currentTimeMillis();
        System.out.println(myPow2(0.00001, 2147483647));
        System.out.println("cost : " + (System.currentTimeMillis()-start));
        System.out.println(myPow3(0.00001, 2147483647, new HashMap<>()));
        System.out.println("cost : " + (System.currentTimeMillis()-start));
        System.out.println(myPow4(0.00001, 2147483647));
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


    public double myPow3(double x, int n, Map<Integer, Double> map){
        if (map.containsKey(n)){
            return map.get(n);
        }
        if (n<0){
            return 1/myPow3(x, -n, map);
        }
        if (n==0){
            return 1;
        }
        if (n==1){
            return x;
        }
        double k = myPow3(x, n/2, map);
        double result = k*k;
        if (n%2==1){
            result *= x;
        }
        map.put(n, result);
        return result;
    }


    public double myPow4(double x, int n){
        if (n<0){
            return 1/myPow4(x, -n);
        }
        double result = 1.0;
        while (n>0){
            if (n%2==1){
                result *= x;
            }
            n>>=1;
            x *=x;
        }
        return result;
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
