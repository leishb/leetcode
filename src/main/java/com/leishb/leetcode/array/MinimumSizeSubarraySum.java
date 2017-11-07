package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.BinarySearch;
import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/6.
 */
@BinarySearch
public class MinimumSizeSubarraySum {

    @Test
    public void test(){
        Assert.assertTrue(minSubArrayLen(11, new int[]{1,2,3,4,5})==3);
        Assert.assertTrue(minSubArrayLen(16, new int[]{1,2,3,4,5})==0);

        Assert.assertTrue(minSubArrayLen2(11, new int[]{1,2,3,4,5})==3);
        Assert.assertTrue(minSubArrayLen2(16, new int[]{1,2,3,4,5})==0);
    }


    /**
     * Accepted
     * @param s
     * @param nums
     * @return
     */
    @TwoPointers
    public int minSubArrayLen2(int s, int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int i=0, j=0, sum=0, min =Integer.MAX_VALUE;
        while (j<nums.length){
            sum += nums[j++];
            while (sum>=s){
                min = Math.min(min, j-i);
                sum -= nums[i++];
            }
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * Accepted
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        int low = 0;
        int high = nums.length;
        if (high==0){
            return 0;
        }
        int[] sums = new int[nums.length+1];
        sums[0]=0;
        for (int i=0;i<nums.length;i++){
            sums[i+1] = sums[i]+nums[i];
        }
        int result = high+1;
        while (low<=high){
            int mid = (low+high)/2;
            boolean midSum = false;
            for (int i=0;i+mid-1<nums.length;i++){
                if (sums[i+mid]-sums[i] >=s){
                    midSum = true;
                    result = mid;
                }
            }
            if (midSum){
                high=mid-1;
            }else {
                low=mid+1;
            }
        }
        return result==nums.length+1 ? 0 : result;
    }


    private boolean windowExist(int size, int[] nums, int s){
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            if (i>=size){
                sum -= nums[i-size];
            }
            sum+=nums[i];
            if (sum>=s){
                return true;
            }
        }
        return false;
    }
}
