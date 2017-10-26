package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.TwoPointers;

/**
 * Created by me on 2017/10/23.
 */
@TwoPointers
public class RemoveDuplicateFromArray {

    /**
     * Accepted
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        int count = 0;
        for (int i=1;i<nums.length;i++){
            if (nums[i]!=nums[i-1]){
                count++;
                nums[count] = nums[i];
            }
        }
        return count+1;
    }


    /**
     * Accepted
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i=0;
        for(int j=0;j<nums.length;j++){
            if(nums[j] != val){
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }
}
