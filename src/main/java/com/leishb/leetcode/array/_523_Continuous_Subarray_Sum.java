package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/9.
 */
public class _523_Continuous_Subarray_Sum {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            sum += nums[i];
            sum = k==0?sum:sum%k;
            map.putIfAbsent(sum, i);
            if (i-map.get(sum) >= 2){
                return true;
            }
        }
        return false;
    }
}
