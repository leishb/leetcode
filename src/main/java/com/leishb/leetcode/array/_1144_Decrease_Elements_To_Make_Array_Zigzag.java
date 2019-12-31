package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/25.
 */
public class _1144_Decrease_Elements_To_Make_Array_Zigzag {



    public int movesToMakeZigzag(int[] nums) {
        int evenGreater = 0;
        for (int i=0;i<nums.length;i++){
            if (i%2==0)continue;
            int step = 0;
            if (i>0 && nums[i] >= nums[i-1]){
                step = nums[i]-nums[i-1]+1;
            }
            if (i+1<nums.length && nums[i] >= nums[i+1]){
                step = Math.max(step, nums[i]-nums[i+1]+1);
            }
            evenGreater += step;
        }
        int oddGreater = 0;
        for (int i=0;i<nums.length;i++){
            if (i%2==1)continue;
            int step = 0;
            if (i>0 && nums[i] >= nums[i-1]){
                step = nums[i]-nums[i-1]+1;
            }
            if (i+1<nums.length && nums[i] >= nums[i+1]){
                step = Math.max(step, nums[i]-nums[i+1]+1);
            }
            oddGreater += step;
        }
        return Math.min(evenGreater , oddGreater);
    }
}
