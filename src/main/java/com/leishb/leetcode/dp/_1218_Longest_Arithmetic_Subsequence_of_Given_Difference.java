package com.leishb.leetcode.dp;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/14.
 */
public class _1218_Longest_Arithmetic_Subsequence_of_Given_Difference {


    /**
     * Accepted
     * @param arr
     * @param difference
     * @return
     */
    public int longestSubsequence(int[] arr, int difference) {
        int max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<arr.length;i++){
            int cur = Math.max(map.getOrDefault(arr[i], 0), map.getOrDefault(arr[i]-difference, 0)+1);
            map.put(arr[i], cur);
            max = Math.max(max, cur);
        }
        return max;
    }
}
