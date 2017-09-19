package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/19.
 */
public class Dividend2Nums {

    @Test
    public void testDivide(){
        Assert.assertTrue(dividend(8,2)==4);
        Assert.assertTrue(dividend(9,2)==4);
        Assert.assertTrue(dividend(0,2)==0);
        Assert.assertTrue(dividend(-10,2)==-5);
        Assert.assertTrue(dividend(10,-3)==-3);
        Assert.assertTrue(dividend(4247,2123)==2);
        Assert.assertTrue(dividend(Integer.MAX_VALUE,-1)==-Integer.MAX_VALUE);
        Assert.assertTrue(dividend(Integer.MAX_VALUE,Integer.MIN_VALUE)==0);
        Assert.assertTrue(dividend(Integer.MIN_VALUE,Integer.MAX_VALUE)==-1);
    }


    public int dividend(int dividend, int divisor){

        if (divisor == 1){
            return dividend;
        }
        if (divisor == 0){
            return Integer.MAX_VALUE;
        }
        if (dividend==Integer.MIN_VALUE && divisor == -1){
            return Integer.MAX_VALUE;
        }
        //attention : 使用long参数的方法，将参数强制转换为long
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long) divisor);
        int sign = 1;
        if (dividend <0 && divisor >0){
            sign=-1;
        }
        if (dividend>0&&divisor<0){
            sign = -1;
        }
        int result = 0;
        while (ldividend>=ldivisor){
            long ld = ldivisor;
            int mul = 1;
            while(ldividend >=(ld<<1)){
                ld<<=1;
                mul<<=1;
            }
            ldividend-=ld;
            result+=mul;
        }

        return sign*result;
    }
}
