package com.leishb.leetcode.array;

/**
 * Created by me on 2020/1/15.
 */
public class _162_Find_Peak_Element {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int n = nums.length;
        if (nums.length<=1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n-1] > nums[n-2]) return n-1;
        for (int i=1;i<n-1;i++){
            if (nums[i] > nums[i-1] && nums[i] > nums[i+1]){
                return i;
            }
        }
        return -1;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int findPeakElement2(int[] nums) {
        int l = 0, r = nums.length-1;
        while (l<r){
            int m = (l+r)/2;
            if (nums[m] < nums[m+1]){
                l = m+1;
            }else {
                r = m;
            }
        }
        return l;
    }
}
