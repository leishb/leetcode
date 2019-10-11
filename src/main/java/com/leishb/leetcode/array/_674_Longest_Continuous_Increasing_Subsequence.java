package com.leishb.leetcode.array;

/**
 * Created by me on 2019/10/9.
 */
public class _674_Longest_Continuous_Increasing_Subsequence {

    public int findLengthOfLCIS(int[] nums) {
        if (nums.length==0) return 0;
        int max = 1;
        int cur = 1;
        for (int i=1;i<nums.length;i++){
            if (nums[i]>nums[i-1]){
                cur +=1;
            }else {
                cur = 1;
            }
            max = Math.max(cur, max);
        }
        return max;
    }
}
