package com.leishb.leetcode.dp;

import java.util.Arrays;

/**
 * Created by me on 2019/12/10.
 */
public class _300_Longest_Increasing_Subsequence {


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length==0) return 0;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int ans = 1;
        for (int i=1;i<nums.length;i++){
            dp[i] = 1;
            for (int j=i-1;j>=0;j--){
                if (nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }



    public int lengthOfLIS2(int[] nums) {
        if (nums.length==0) return 0;
        int[] tail = new int[nums.length];
        int size = 0;
        for (int num : nums){
            int i = 0, j = size-1;
            while (i<=j){
                int m = (i+j)/2;
                if (tail[m] < num){
                    i = m+1;
                }else {
                    j = m-1;
                }
            }
            tail[i] = num;
            if (i==size)size++;
        }
        return size;
    }


    public int lengthOfLIS3(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums){
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i<0) i = -(i+1);
            dp[i] = num;
            if (i==len)len++;
        }
        return len;
    }
}
