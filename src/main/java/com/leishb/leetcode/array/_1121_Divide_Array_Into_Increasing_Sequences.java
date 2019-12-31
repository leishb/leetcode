package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/10.
 */
public class _1121_Divide_Array_Into_Increasing_Sequences {


    /**
     * Accepted
     * @param nums
     * @param K
     * @return
     */
    public boolean canDivideIntoSubsequences(int[] nums, int K) {
        int cur =0, groups = 1;
        for (int i=1;i<nums.length;i++){
            cur = nums[i]==nums[i-1]?cur+1:1;
            groups = Math.max(cur, groups);
        }
        return groups*K<=nums.length;
    }
}
