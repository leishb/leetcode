package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/10/29.
 */
public class _1060_Missing_Element_in_Sorted_Array {



    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        if (missingNum(n-1, nums) < k){
            return nums[n-1]+ k - missingNum(n-1, nums);
        }
        int idx = 1;
        while (missingNum(idx, nums) < k) idx++;
        return nums[idx-1]+k-missingNum(idx-1, nums);
    }

    private int missingNum(int idx, int[] nums){
        return nums[idx]-nums[0]-idx;
    }



    public int missingElement2(int[] nums, int k) {
        int n = nums.length;
        if (missingNum(n-1, nums) < k){
            return nums[n-1]+k-missingNum(n-1, nums);
        }
        int l = 0, r = n-1, idx = n-1;
        while (l<=r){
            int mid = (l+r)/2;
            if (missingNum(mid, nums) >= k){
                idx = Math.min(idx, mid);
                r = mid -1;
            }else {
                l = mid +1;
            }
        }
        return nums[idx-1]+k-missingNum(idx-1, nums);
    }


    @Test
    public void test(){
        Assert.assertTrue(missingElement2(new int[]{4,7,9, 10}, 1)==5);
        Assert.assertTrue(missingElement2(new int[]{2 ,3, 5, 7}, 1)==4);
    }
}
