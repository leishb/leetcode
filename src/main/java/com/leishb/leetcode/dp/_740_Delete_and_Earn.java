package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/7.
 */
public class _740_Delete_and_Earn {


    public int deleteAndEarn(int[] nums) {
        int[] sum = new int[10001];
        for(int num : nums){
            sum[num] += num;
        }
        int[] dp = new int[10001];
        dp[1] = sum[1];
        for (int i=2;i<dp.length;i++){
            dp[i] = Math.max(dp[i-2]+sum[i], dp[i-1]);//pick or not
        }
        return dp[dp.length-1];
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int deleteAndEarn2(int[] nums) {
        int[] sum = new int[10001];
        for(int num : nums){
            sum[num] += num;
        }
        int[][] dp = new int[10001][2];
        dp[1][1] = sum[1];
        for (int i=2;i<dp.length;i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);//not pick
            dp[i][1] = dp[i-1][0]+sum[i]; //pick
        }
        return Math.max(dp[dp.length-1][0], dp[dp.length-1][1]);
    }
    @Test
    public void test(){
        Assert.assertTrue(deleteAndEarn2(new int[]{3,2,4})==6);
        Assert.assertTrue(deleteAndEarn2(new int[]{2, 2, 3, 3, 3, 4})==9);
    }
}
