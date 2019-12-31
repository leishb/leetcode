package com.leishb.leetcode.dp;

import com.leishb.leetcode.tag.BinarySearch;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/11/4.
 */
@BinarySearch
public class _410_Split_Array_Largest_Sum {


    /**
     * Accepted
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        int[][] dp = new int[m+1][nums.length];
        int[] sums = new int[nums.length+1];
        for (int i=0;i<nums.length;i++){
            sums[i+1] = sums[i] + nums[i];
        }
        for (int i=0;i<nums.length;i++){
            dp[1][i] = sums[i+1];
        }
        for (int i=2;i<=m;i++){
            for (int j=i-1;j<nums.length;j++){
                dp[i][j] = Integer.MAX_VALUE;
                for (int k=j-1;k>=0;k--){
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[i-1][k], sums[j+1]-sums[k+1]));
                }
            }
        }
        return dp[m][nums.length-1];
    }


    /**
     * Accepted
     * @param nums
     * @param m
     * @return
     */
    public int splitArray2(int[] nums, int m) {
        int max = 0;
        long sum = 0;
        for (int num : nums){
            sum += num;
            max = Math.max(max, num);
        }
        if (m==1) return (int) sum;
        long l = max, r = sum;
        long ans = 0;
        while (l<=r){
            long mid = (l+r)/2;
            if (valid(nums, m, mid)){
                r = mid -1;
                ans = mid;
            }else {
                l = mid +1;
            }
        }
        return (int) ans;
    }

    private boolean valid(int[] nums, int m, long sum){
        int count = 1;
        long total = 0;
        for (int num : nums){
            total += num;
            if (total > sum){
                count++;
                total = num;
                if (count > m){
                    return false;
                }
            }
        }
        return true;
    }



    @Test
    public void test(){
        Assert.assertTrue(splitArray2(new int[]{7,2,5,10,8}, 2)==18);
        Assert.assertTrue(splitArray2(new int[]{10, 2,3}, 2)==10);
    }
}
