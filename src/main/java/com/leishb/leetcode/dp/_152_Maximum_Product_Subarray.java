package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/10/16.
 */
public class _152_Maximum_Product_Subarray {

    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];
        int ans = max[0];
        for (int i=1;i<nums.length;i++){
            max[i] = Math.max(Math.max(max[i-1] * nums[i], min[i-1] * nums[i]), nums[i]);
            min[i] = Math.min(Math.min(max[i-1] * nums[i], min[i-1] * nums[i]), nums[i]);
            ans = Math.max(ans, max[i]);
        }
        return ans;
    }


    public int maxProduct2(int[] nums) {
        int max = nums[0];
        int min = nums[0];
        int ans = max;
        for (int i=1;i<nums.length;i++){
            int tempMax = max;
            max = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i] * tempMax, nums[i] * min));
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
