package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/23.
 */
@TwoPointers
public class RemoveDuplicateFromArray {

    @Test
    public void test(){
        Assert.assertTrue(removeDuplicatesT(new int[]{1,1,1})==2);
    }


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
     * <br>https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/</br>
     * @param nums
     * @return
     */
    public int removeDuplicatesT(int[] nums) {
        int i=1;
        int len=1;
        for(int j=1;j<nums.length;j++){
            if(nums[j]==nums[j-1]){
                if(len<2){
                    nums[i]=nums[j];
                    len++;
                    i++;
                }
            }
            if(nums[j]!=nums[j-1]){
                nums[i]=nums[j];
                i++;
                len=1;
            }
        }
        return i;
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
