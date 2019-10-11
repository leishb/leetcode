package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/9/26.
 */
public class _689_Maximum_Sum_of_3_Non_Overlapping_Subarrays {

    /**
     * Accepted
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n], right = new int[n], sum = new int[n+1], ans = new int[3];
        for (int i=1;i<=n;i++)sum[i] = sum[i-1]+nums[i-1];
        //from left to right
        int total = sum[k]-sum[0];
        for (int i=k;i<n;i++){
            if (sum[i+1] - sum[i+1-k] > total){
                total = sum[i+1] - sum[i+1-k];
                left[i] = i-k+1;
            }else {
                left[i] = left[i-1];
            }
        }
        //right
        total = sum[n]-sum[n-k];
        right[n-k] = n-k;
        for (int i=n-k-1;i>=0;i--){
            if (sum[i+k]-sum[i] >= total){
                total = sum[i+k]-sum[i];
                right[i] = i;
            }else {
                right[i] = right[i+1];
            }
        }
        //middle
        total = 0;
        for (int i=k;i<=n-2*k;i++){
            int l = left[i-1], r = right[i+k];
            int max = sum[l+k]-sum[l] + sum[i+k] - sum[i] + sum[r+k]-sum[r];
            if (max > total){
                total = max;
                ans = new int[]{l, i , r};
            }
        }
        return ans;
    }
}
