package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DivideAndConquer;
import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/9.
 */
public class MaximunProductSubArray {

    @Test
    public void test(){
        Assert.assertTrue(maxProduct(new int[]{2,3,-1,4})==6);
        Assert.assertTrue(maxProduct(new int[]{-2,3,-4})==24);
        Assert.assertTrue(maxProduct(new int[]{-2,3})==3);
        Assert.assertTrue(maxProduct(new int[]{-2,0,1,-2})==1);
        Assert.assertTrue(maxProduct(new int[]{-2,0,1,8,-2,-8})==128);

        Assert.assertTrue(maxProduct2(new int[]{2,3,-1,4})==6);
        Assert.assertTrue(maxProduct2(new int[]{-2,3,-4})==24);
        Assert.assertTrue(maxProduct2(new int[]{-2,3})==3);
        Assert.assertTrue(maxProduct2(new int[]{-2,0,1,-2})==1);
        Assert.assertTrue(maxProduct2(new int[]{-2,0,1,8,-2,-8})==128);


        Assert.assertTrue(maxProduct3(new int[]{2,3,-1,4})==6);
        Assert.assertTrue(maxProduct3(new int[]{-2,3,-4})==24);
        Assert.assertTrue(maxProduct3(new int[]{-2,3})==3);
        Assert.assertTrue(maxProduct3(new int[]{-2,0,1,-2})==1);
        Assert.assertTrue(maxProduct3(new int[]{-2,0,1,8,-2,-8})==128);
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    @DivideAndConquer
    public int maxProduct(int[] nums){
        return div(nums, 0, nums.length-1);
    }

    private int div(int[] nums, int start, int end){
        if (start>end){
            return Integer.MIN_VALUE;
        }
        if (start==end){
            return nums[start];
        }
        int mid = (start+end)/2;
        int left = div(nums, start, mid-1);
        int right = div(nums, mid+1, end);
        int prd = 1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int midMax = Integer.MIN_VALUE;
        for (int i=mid;i>=start;i--){
            prd *= nums[i];
            max = Math.max(max, prd);
            min = Math.min(min, prd);
            midMax = Math.max(midMax, max);
        }
        for (int i=mid+1;i<=end;i++){
            int temp = max;
            max = Math.max(nums[i]*max, nums[i]*min);
            min = Math.min(nums[i]*temp, nums[i]*min);
            midMax = Math.max(max, midMax);
        }
        return Math.max(left, Math.max(midMax, right));
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    @DynamicProgramming
    public int maxProduct2(int[] nums){
        if (nums.length==0){
            return 0;
        }
        int[] dp = new int[nums.length];
        int max = nums[0];
        int min = nums[0];
        dp[0]=max;
        for (int i=1;i<nums.length;i++){
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            dp[i]=max;
        }
        for (int i=0;i<dp.length;i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    public int maxProduct3(int[] nums){
        if (nums.length==0){
            return 0;
        }
        int max = nums[0];
        int min = nums[0];
        int result = max;
        for (int i=1;i<nums.length;i++){
            int temp = max;
            max = Math.max(Math.max(max * nums[i], min * nums[i]), nums[i]);
            min = Math.min(Math.min(temp * nums[i], min * nums[i]), nums[i]);
            result = Math.max(result, max);
        }
        return result;
    }
}
