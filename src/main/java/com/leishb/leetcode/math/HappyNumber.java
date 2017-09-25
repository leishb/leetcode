package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2017/9/25.
 */
public class HappyNumber {

    @Test
    public void test(){

        Assert.assertTrue(isHappyNumber(68));
        Assert.assertTrue(!isHappyNumber(14));

    }


    public boolean isHappyNumber(int n){
        Set<Integer> set = new HashSet<Integer>();
        while (n!=1){
            int sumOfSqt = sumOfSqt(n);
            if (set.contains(sumOfSqt)){
                return false;
            }else {
                set.add(sumOfSqt);
            }
            n = sumOfSqt;
        }
        return true;
    }


    private int sumOfSqt(int n){
        int sum = 0;
        while (n!=0){
            int mode = n%10;
            sum += mode*mode;
            n/=10;
        }
        return sum;
    }
}
