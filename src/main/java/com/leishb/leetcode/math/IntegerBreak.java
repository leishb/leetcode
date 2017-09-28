package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/9/28.
 */
public class IntegerBreak {

    @Test
    public void test(){
        Assert.assertTrue(integerBreak(0)==0);
        Assert.assertTrue(integerBreak(1)==0);
        Assert.assertTrue(integerBreak(2)==1);
        Assert.assertTrue(integerBreak(3)==2);
        Assert.assertTrue(integerBreak(4)==4);
        Assert.assertTrue(integerBreak(10)==36);
    }


    /**
     * Accepted
     * @param n
     * @return
     */
    public int integerBreak(int n){
        int[] dp = new int[n+1];
        for (int i=0;i<=n;i++){
            int max=0;
            for (int j=0;j<i;j++){
                max=Math.max(dp[i-j]*j, max);
                max=Math.max(max, (i-j)*j);
            }
            dp[i]=max;
        }
        return dp[n];
    }
}
