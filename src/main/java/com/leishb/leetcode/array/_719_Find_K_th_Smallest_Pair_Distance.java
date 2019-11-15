package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/11/13.
 */
public class _719_Find_K_th_Smallest_Pair_Distance {


    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int lo = 0, hi = nums[nums.length-1]-nums[0];
        while (lo<=hi){
            int mid = (hi-lo)/2+lo;
            int less = getLessEqual(nums, mid);
            if (less< k){
                lo = mid + 1;
            }else {
                hi = mid - 1;
            }
        }
        return lo;
    }


    private int getLessEqual(int[] nums, int val){
        int j = 0, sum = 0, count = 0;
        for (int i=1;i<nums.length;i++){
            sum += nums[i]-nums[i-1];
            while (sum > val){
                sum -= nums[j+1]-nums[j];
                j++;
            }
            count += i-j;
        }
        return count;
    }


    @Test
    public void test(){
        Assert.assertTrue(smallestDistancePair(new int[]{1,3,1}, 1)==0);
    }
}
