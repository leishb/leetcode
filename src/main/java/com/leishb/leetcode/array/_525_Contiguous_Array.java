package com.leishb.leetcode.array;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/15.
 */
public class _525_Contiguous_Array {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int findMaxLength(int[] nums) {
        int maxLen = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0)nums[i]=-1;
        }
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int sum = 0;
        sumToIndex.put(0, -1);
        for (int i=0;i<nums.length;i++){
            sum += nums[i];
            if (sumToIndex.containsKey(sum)){
                maxLen = Math.max(maxLen, i- sumToIndex.get(sum));
            }else {
                sumToIndex.put(sum, i);
            }
        }
        return maxLen;
    }

    /**
     * Accepted
     * @param nums
     * @return
     */
    public int findMaxLength3(int[] nums) {
        int maxLen = 0;
        Map<Integer, Integer> sumToIndex = new HashMap<>();
        int zeros = 0;
        int ones = 0;
        sumToIndex.put(0, -1);
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                zeros++;
            }else {
                ones++;
            }
            if (sumToIndex.containsKey(ones-zeros)){
                maxLen = Math.max(maxLen, i- sumToIndex.get(ones-zeros));
            }else {
                sumToIndex.put(ones-zeros, i);
            }
        }
        return maxLen;
    }

    @Test
    public void test(){
        findMaxLength(new int[]{0,1});
    }
}
