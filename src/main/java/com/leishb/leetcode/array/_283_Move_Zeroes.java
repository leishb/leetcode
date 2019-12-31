package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/9.
 */
public class _283_Move_Zeroes {


    /**
     * Accepted
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        int j = 0;
        for(int i=0;i<nums.length;i++){
            while (j<=i && nums[j]!=0){
                j++;
            }
            if (j<i && nums[i]!=0){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
    }


    public void moveZeroes2(int[] nums) {
        int j = 0;
        for(int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                nums[j++] = nums[i];
            }
        }
        while (j<nums.length){
            nums[j++] = 0;
        }
    }
}
