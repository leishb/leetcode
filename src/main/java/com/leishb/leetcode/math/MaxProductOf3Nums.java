package com.leishb.leetcode.math;

import java.util.Arrays;

/**
 * Created by me on 2017/10/17.
 */
public class MaxProductOf3Nums {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int maximumProduct2(int[] nums) {
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        for (int num : nums){
            if (num >= max1){// num is bigger than max1, max2, max3
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if (num >= max2){ // num is between max2 and max1
                max3 = max2;
                max2 = num;
            }else if (num >= max3){ // num is bigger than max3 and lesser than max1 and max2
                max3 = num;
            }

            if (num <= min1){// num is smaller than min1
                min2 = min1;
                min1 = num;
            }else if (num <= min2){
                min2 = num;
            }
        }
        return Math.max(max1 * max2 * max3, min1 * min2 * max1);
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        if(nums.length <= 6){
            return getMax(nums);
        }
        Arrays.sort(nums);
        int len = nums.length;
        int[] minNums = new int[]{nums[0], nums[1], nums[2], nums[len-1], nums[len-2], nums[len-3]};
        return getMax(minNums);
    }



    private int getMax(int[] nums){
        int max = Integer.MIN_VALUE;
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){
                for (int k=j+1;j<nums.length;k++){
                    max = Math.max(max, nums[i] * nums[j] * nums[k]);
                }
            }
        }
        return max;
    }
}
