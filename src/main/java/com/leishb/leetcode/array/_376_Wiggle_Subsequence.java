package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/10/30.
 */
public class _376_Wiggle_Subsequence {


    /**
     * Accepted
     * [1,7,4,9,2,5]
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums.length<2) return nums.length;
        int[][] dp = new int[nums.length][2];
        dp[0] = new int[]{1,1};
        int max = 1;
        for (int i=1;i<nums.length;i++){
            for (int j=i-1;j>=0;j--){
                if (nums[j] > nums[i]){
                    dp[i][0] = Math.max(dp[i][0], dp[j][1]+1);
                    dp[i][1] = Math.max(dp[i][1] ,1);
                }else if (nums[j] < nums[i]){
                    dp[i][1] = Math.max(dp[i][1], dp[j][0]+1);
                    dp[i][0] = Math.max(dp[i][0] ,1);
                }else {
                    dp[i][1] = Math.max(dp[i][1] ,1);
                    dp[i][0] = Math.max(dp[i][0] ,1);
                }
            }
            max = Math.max(max, Math.max(dp[i][0], dp[i][1]));
        }
        return max;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int wiggleMaxLength2(int[] nums) {
        if (nums.length<2) return nums.length;
        int[] up = new int[nums.length];
        int[] down = new int[nums.length];
        up[0] = 1;
        down[0] = 1;
        for (int i=1;i<nums.length;i++){
            if (nums[i] > nums[i-1]){
                up[i] = down[i-1] +1;
                down[i] = down[i-1];
            }else if (nums[i] < nums[i-1]){
                down[i] = up[i-1] + 1;
                up[i] = up[i-1];
            }else {
                up[i] = up[i-1];
                down[i] = down[i-1];
            }
        }
        return Math.max(up[nums.length-1], down[nums.length-1]);
    }


    @Test
    public void test(){
        Assert.assertTrue(wiggleMaxLength2(new int[]{1,7,4,9,2,5})==6);
    }
}
