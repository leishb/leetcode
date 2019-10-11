package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/10/9.
 */
public class _651_4_Keys_Keyboard {

    public int maxA(int N) {
        int[] dp = new int[N+1];
        for (int i=1;i<=N;i++){
            dp[i] = dp[i-1]+1;
            for (int j=2;j<i;j++){
                dp[i] = Math.max(dp[i], dp[j-2] * (i-j+1));
            }
        }
        return dp[N];
    }
    @Test
    public void test(){
        Assert.assertTrue(maxA(5)==5);
        Assert.assertTrue(maxA(6)==6);
        Assert.assertTrue(maxA(7)==9);
    }
}
