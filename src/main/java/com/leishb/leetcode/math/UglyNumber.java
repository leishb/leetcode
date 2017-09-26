package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2017/9/26.
 */
public class UglyNumber {


    @Test
    public void testUglyNum(){
//        Assert.assertTrue(isUglyNum(30));
//        Assert.assertTrue(!isUglyNum(17));

        Assert.assertTrue(nthSuperUglyNum(3, new int[]{2,3,5})==3);
    }

    /**
     * Accepted
     * @param num
     * @return
     */
    public boolean isUglyNum(int num){
        if (num<=0){
            return false;
        }
        while (num%2==0){
            num/=2;
        }
        while (num%3==0){
            num/=3;
        }
        while (num%5==0){
            num/=5;
        }
        return num==1;
    }


    /**
     *
     * Accepted
     * ugly num 列表自己构建自己，将构建好的数字分别乘 2,3,5
     * 双层循环，分别将list中的数字，按顺序乘（2,3,5）
     * http://www.geeksforgeeks.org/ugly-numbers/
     * @param n
     * @return
     */
    public int nthUglyNum(int n){
        int a = 0;
        int b = 0;
        int c = 0;
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        while (list.size()<n){
            int nextVal = Math.min(list.get(a)*2, Math.min(list.get(b)*3, list.get(c)*5));
            list.add(nextVal);
            if (nextVal==list.get(a)*2){
                a++;
            }
            if (nextVal==list.get(b)*3){
                b++;
            }
            if (nextVal==list.get(c)*5){
                c++;
            }
        }
        return list.get(n-1);
    }


    /**
     * Accepted
     * @param n
     * @param primes
     * @return
     */
    public int nthSuperUglyNum(int n, int[] primes){
        int[] indexes = new int[primes.length];
        int[] uglyNums = new int[n];
        uglyNums[0]=1;
        int k=1;
        while (k<n){
            int min = Integer.MAX_VALUE;
            for (int i =0;i<indexes.length;i++){
                int index = indexes[i];
                if (uglyNums[index] * primes[i]<=min){
                    min = uglyNums[index] * primes[i];
                }
            }
            for (int i =0;i<indexes.length;i++){
                int index = indexes[i];
                if (uglyNums[index] * primes[i]==min){
                    indexes[i]++;
                }
            }
            uglyNums[k++]=min;
        }
        return uglyNums[n-1];
    }
}
