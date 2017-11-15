package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.Hash;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/11/13.
 */
@Hash
public class LongestConsecutiveSequence {

    @Test
    public void test(){
        Assert.assertTrue(longestConsecutive2(new int[]{4,3,5,1,2})==5);
        Assert.assertTrue(longestConsecutive2(new int[]{1,1,2,3,2,4})==4);
        Assert.assertTrue(longestConsecutive2(new int[]{1,1})==1);
        Assert.assertTrue(longestConsecutive2(new int[]{1})==1);
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int max=0;
        for (int i=0;i<nums.length;i++){
            if (!map.containsKey(nums[i])){
                int left=map.getOrDefault(nums[i]-1, 0);
                int right=map.getOrDefault(nums[i]+1, 0);
                int sum = left+right+1;

                map.put(nums[i], sum);

                max = Math.max(max, sum);

                map.put(nums[i]-left, sum);
                map.put(nums[i]+right, sum);
            }
        }
        return max;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int longestConsecutive2(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        int max=1;
        Arrays.sort(nums);
        int cur = nums[0];
        for (int i=1;i<nums.length;i++){
            if (nums[i]==nums[i-1] || nums[i]-nums[i-1]==1){
                max = Math.max(nums[i]-cur+1, max);
            }else {
                cur = nums[i];
            }
        }
        return max;
    }
}
