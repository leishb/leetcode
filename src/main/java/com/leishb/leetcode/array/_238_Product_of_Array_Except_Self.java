package com.leishb.leetcode.array;

/**
 * Created by me on 2019/10/16.
 */
public class _238_Product_of_Array_Except_Self {

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        for (int i=1;i<nums.length;i++){
            left[i] = left[i-1] * nums[i-1];
        }
        int[] right = new int[nums.length];
        right[nums.length-1] = 1;
        for (int i=nums.length-2;i>=0;i--){
            right[i] = right[i+1] * nums[i+1];
        }
        int[] ans = new int[nums.length];
        for (int i=0;i<ans.length;i++){
            ans[i] = left[i] * right[i];
        }
        return ans;
    }



    public int[] productExceptSelf2(int[] nums) {
        int[] left = new int[nums.length];
        left[0] = 1;
        for (int i=1;i<nums.length;i++){
            left[i] = left[i-1] * nums[i-1];
        }
        int k = 1;
        for (int i=nums.length-1;i>=0;i--){
            left[i] = left[i]*k;
             k *= nums[i];
        }
        return left;
    }
}
