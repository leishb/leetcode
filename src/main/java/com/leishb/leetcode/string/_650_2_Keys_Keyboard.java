package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/7/24.
 */
public class _650_2_Keys_Keyboard {


    /**
     * Accepted
     * @param n
     * @return
     */
    public int minSteps(int n) {
        int[] dp = new int[n+1];
        for (int i=2;i<=n;i++){
            dp[i] = i;
            for (int j=2;j<i/2;j++){
                if (i%j==0){
                    dp[i] = Math.min(dp[i], dp[i/j]+j);
                }
            }
        }
        return dp[n];
    }


    public int minSteps2(int n) {
        int[] dp = new int[n+1];
        for (int i=2;i<=n;i++){
            dp[i] = i;
            for (int j=i-1;j>=2;j--){
                if (i%j==0){
                    dp[i] = Math.min(dp[i], dp[j]+i/j);
                    break;
                }
            }
        }
        return dp[n];
    }

    @Test
    public void test(){
        Assert.assertTrue(minSteps2(3)==3);
        Assert.assertTrue(minSteps2(2)==2);
        Assert.assertTrue(minSteps2(1)==0);
        Assert.assertTrue(minSteps2(12)==7);
    }
}
