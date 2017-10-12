package com.leishb.leetcode.math;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/12.
 */
public class MinMoves {

    @Test
    public void test(){
        Assert.assertTrue(minMoves(new int[]{1,2,3})==3);
    }


    /**
     * Accepted
     * sum + k * (n-1) = m * n, k is the moves, [m,m,m,m...]. minNum + k = m. sum+ k*(n-1)=(minNum+k)*n; k=sum-minNum*n;
     * 数学问题，可以考虑总加和 sum
     * @param nums
     * @return
     */
    public int minMoves(int[] nums){
        int sum = nums[0];
        int min = nums[0];
        for (int i=1;i<nums.length;i++){
            sum += nums[i];
            if (nums[i]<min){
                min = nums[i];
            }
        }
        int count = sum - min*nums.length;
        return count;
    }
}
