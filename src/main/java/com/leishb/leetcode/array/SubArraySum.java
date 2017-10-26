package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/10/24.
 */
public class SubArraySum {


    @Test
    public void test(){
        Assert.assertTrue(subArraySumOfK(new int[]{1,1,1}, 2)==2);
        Assert.assertTrue(subArraySumOfK(new int[]{1,2,3}, 6)==1);
        Assert.assertTrue(subArraySumOfK(new int[]{1}, 1)==1);
        Assert.assertTrue(subArraySumOfK(new int[]{1}, 2)==0);
        Assert.assertTrue(subArraySumOfK(new int[]{0,0,0,0}, 0)==10);


        Assert.assertTrue(subArraySum(new int[]{1,1,1}, 2)==2);
        Assert.assertTrue(subArraySum(new int[]{1,2,3}, 6)==1);
        Assert.assertTrue(subArraySum(new int[]{1}, 1)==1);
        Assert.assertTrue(subArraySum(new int[]{1}, 2)==0);
        Assert.assertTrue(subArraySum(new int[]{0,0,0,0}, 0)==10);
    }


    public int subArraySumOfK(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int count = 0;
        map.put(0,1);
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            sum += nums[i];
            count += map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum-k, 0)+1);
        }
        return count;
    }


    /**
     *
     * @param nums
     * @param k
     * @return
     */
    public int subArraySum(int[] nums, int k){
        int count = 0;
        for (int i=0;i<nums.length;i++){
            int sum = 0;//保存以i 开头的子数组的和
            for (int j=i;j<nums.length;j++){
                sum += nums[j];
                if (sum == k){
                    count++;
                }
            }
        }
        return count;
    }
}
