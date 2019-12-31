package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/11/26.
 */
public class _644_Maximum_Average_Subarray_II {


    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public double findMaxAverage(int[] nums, int k) {
        double min = nums[0], max = nums[0];
        for (int num : nums){
            min = Math.min(num, min);
            max = Math.max(num, max);
        }
        double ans = 0;
        while (min<=max){
            double mid = (max-min)/2 + min;
            if (check(nums, mid, k)){
                ans = mid ;
                min = mid + 0.000_005;
            }else {
                max = mid - 0.000_005;
            }
        }
        return ans;
    }



    private boolean check(int[] nums, double mid, int k){
        double sum = 0, prev = 0;
        for (int i=0;i<k;i++){
            sum += nums[i]-mid;
        }
        if (sum>=0) return true;
        for (int i=k;i<nums.length;i++){
            if (sum>=0) return true;
            sum += nums[i]-mid;
            prev += nums[i-k]-mid;
            if (prev < 0){
                sum -= prev;
                prev = 0;
            }
        }
        return sum>=0;
    }



    @Test
    public void test(){
        System.out.println(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4));
        System.out.println(findMaxAverage(new int[]{6}, 1));
    }
}
