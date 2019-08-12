package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/8.
 */
public class _198_House_Robber {

    public int rob(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int[] dp = new int[nums.length+1];
        dp[1] = nums[0];
        for(int i=2;i<nums.length+1;i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i-1]);
        }
        return dp[nums.length];
    }


    @Test
    public void test(){
        Assert.assertTrue(rob(new int[]{2,7,9,3,1})==12);
        Assert.assertTrue(rob(new int[]{1,2,3,1})==4);
    }
}
