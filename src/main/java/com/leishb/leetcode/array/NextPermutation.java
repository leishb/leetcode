package com.leishb.leetcode.array;

/**
 * Created by me on 2017/10/23.
 */
public class NextPermutation {

    /**
     * Accepted
     * @param nums
     */
    public void nextPermutation(int[] nums){
        if (nums.length<2){
            return;
        }
        int index = nums.length-2;
        for (int i=nums.length-1;i>0;i--){
            if (nums[i-1] >= nums[i]){ // attention '='
                index--;
            }else {
                break;
            }
        }
        if (index==-1){
            reverse(nums, 0, nums.length-1);
            return;
        }
        for (int i=nums.length-1;i > index;i--){
            if (nums[i] > nums[index]){
                swap(nums, i, index);
                reverse(nums, index+1, nums.length-1);
            }
        }
    }


    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    private void reverse(int[] arr, int i, int j){
        while (i<j){
            swap(arr, i,j);
            i++;
            j--;
        }
    }
}
