package com.leishb.leetcode.dp;

import org.junit.Test;

/**
 * Created by me on 2019/10/15.
 */
public class _312_Burst_Balloons {

    public int maxCoins(int[] nums) {
        return maxCoins(nums, new int[nums.length][nums.length], 0, nums.length-1);
    }



    private int maxCoins(int[] nums,int[][] memo,  int left, int right){
        if (left>right) return 0;
        int max = 0;
        if (memo[left][right] > 0) return memo[left][right];
        for (int i=left;i<=right;i++){
            max = Math.max(max,
                    maxCoins(nums, memo,  left, i-1)
                    + maxCoins(nums, memo, i+1, right)
                    + nums[i] * (left-1>=0?nums[left-1]:1) * (right+1<nums.length?nums[right+1]:1));
        }
        memo[left][right] = max;
        return max;
    }

    @Test
    public void test(){
        maxCoins(new int[]{3,1,5,8});
    }
}
