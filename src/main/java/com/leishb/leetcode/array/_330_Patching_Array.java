package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/27.
 */
public class _330_Patching_Array {


    /**
     * Accepted
     * @param nums
     * @param n
     * @return
     */
    public int minPatches(int[] nums, int n) {
        int i =0 , patches = 0;
        long miss = 1;
        while (miss <= n){
            if (i<nums.length && nums[i] <= miss){
                miss = nums[i] + miss;
                i++;
            }else {
                patches++;
                miss *=2;
            }
        }
        return patches;
    }
}
