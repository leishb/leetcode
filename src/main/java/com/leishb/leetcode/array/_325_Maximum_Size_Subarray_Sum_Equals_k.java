package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/8.
 */
public class _325_Maximum_Size_Subarray_Sum_Equals_k {


    public int maxSubArrayLen(int[] nums, int k) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        for (int i=0;i<nums.length;i++){
            sum += nums[i];
            map.putIfAbsent(sum, i);
            if (map.containsKey(sum-k)){
                ans = Math.max(ans, i-map.get(sum-k));
            }
        }
        return ans;
    }
}
