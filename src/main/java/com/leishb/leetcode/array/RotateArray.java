package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2017/11/15.
 */
public class RotateArray {


    @Test
    public void test(){
        rotate2(new int[]{1,2,3},1);
    }


    /**
     * TLE
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        for (int i=1;i<=k;i++){
            int temp = nums[nums.length-1];
            for (int j=nums.length-1;j>=1;j--){
                nums[j]=nums[j-1];
            }
            nums[0]=temp;
        }
    }


    /**
     * Accepted
     * @param nums
     * @param k
     */
    public void rotate2(int[] nums, int k) {
        int[] copy = Arrays.copyOfRange(nums, 0, nums.length);
        k%=nums.length;
        if (k==0){
            return;
        }
        for (int i=nums.length-k, j=0;i<nums.length;i++, j++){
            nums[j]=copy[i];
        }
        for (int i=0, j=k;i<nums.length-k;i++,j++){
            nums[j]=copy[i];
        }
    }


    /**
     * Accepted
     * @param nums
     * @param k
     */
    public void rotate3(int[] nums, int k) {
        k%=nums.length;
        if (k==0){
            return;
        }
        reverse(nums, 0, nums.length-1);
        reverse(nums, 0, k-1);
        reverse(nums, k, nums.length-1);
    }

    private void reverse(int[] nums, int start, int end){
        while (start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
