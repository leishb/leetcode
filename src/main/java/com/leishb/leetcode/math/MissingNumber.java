package com.leishb.leetcode.math;

/**
 * Created by me on 2017/9/26.
 */
public class MissingNumber {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int missingNum(int[] nums){
        int[] bits = new int[nums.length+1];
        for (int num:nums){
            bits[num]=1;
        }
        for (int i=0;i<bits.length;i++){
            if (bits[i]==0){
                return i;
            }
        }
        return -1;
    }
}
