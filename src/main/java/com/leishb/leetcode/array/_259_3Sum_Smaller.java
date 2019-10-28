package com.leishb.leetcode.array;

import java.util.Arrays;

/**
 * Created by me on 2019/10/28.
 */
public class _259_3Sum_Smaller {


    /**
     * Accepted
     * @param nums
     * @param target
     * @return
     */
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int res = 0;
        for (int i=0;i<nums.length;i++){
            res += smaller(nums, i+1, nums.length-1, target-nums[i]);
        }
        return res;
    }


    private int smaller(int[] nums, int start, int end, int target){
        int i=start, j=end, res=0;
        while (i<j){
            if (nums[i] + nums[j] >= target){
                j--;
            }else {
                res += j-i;
                i++;
            }
        }
        return res;
    }
}
