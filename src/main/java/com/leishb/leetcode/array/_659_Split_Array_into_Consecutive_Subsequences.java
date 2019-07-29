package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/7/29.
 */
public class _659_Split_Array_into_Consecutive_Subsequences {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> appendFreq = new HashMap<>();
        for (int num : nums){
            freq.put(num, freq.getOrDefault(num, 0)+1);
        }
        for (int num : nums){
            if (freq.get(num)==0){
                continue;
            }
            if (appendFreq.getOrDefault(num, 0)>0){
                appendFreq.put(num, appendFreq.get(num)-1);
                appendFreq.put(num+1, appendFreq.getOrDefault(num+1, 0)+1);
            }else if (freq.getOrDefault(num+1,0)>0 && freq.getOrDefault(num+2,0)>0){
                freq.put(num+1, freq.get(num+1)-1);
                freq.put(num+2, freq.get(num+2)-1);
                appendFreq.put(num+3, appendFreq.getOrDefault(num+3,0)+1);
            }else {
                return false;
            }
            freq.put(num, freq.get(num)-1);
        }
        return true;
    }


    @Test
    public void test(){
        Assert.assertTrue(isPossible(new int[]{1,2,3,3,4,5}));
    }
}
