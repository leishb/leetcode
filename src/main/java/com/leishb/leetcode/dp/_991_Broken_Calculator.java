package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/9.
 */
public class _991_Broken_Calculator {

    public int brokenCalc(int X, int Y) {
        int res = 0;
        while (Y > X){
            Y = Y%2==0?Y/2:Y+1;
            res++;
        }
        return res + X-Y;
    }


    @Test
    public void test(){
        Assert.assertTrue(brokenCalc(2,3)==2);
        Assert.assertTrue(brokenCalc(3,5)==2);
    }
}
