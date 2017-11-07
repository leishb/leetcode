package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/6.
 */
public class LargestRectangleArea {


    @Test
    public void test(){
        Assert.assertTrue(largestRectangleArea(new int[]{2,1,5,6,2,3})==10);
        Assert.assertTrue(largestRectangleArea2(new int[]{2,1,5,6,2,3})==10);
        Assert.assertTrue(largestRectangleArea3(new int[]{2,1,5,6,2,3})==10);
    }


    public int largestRectangleArea(int[] heights) {
        int result = 0;
        for (int i=0;i<heights.length;i++){
            int min = heights[i];
            for (int j=i;j<heights.length;j++){
                min = Math.min(min, heights[j]);
                result = Math.max((j-i+1) * min, result);
            }
        }
        return result;
    }


    public int largestRectangleArea2(int[] heights) {
        if (heights.length==0){
            return 0;
        }
        return div(heights, 0, heights.length-1);
    }



    private int div(int[] heights, int start, int end){
        if (start>end){
            return 0;
        }
        if (start==end){
            return heights[start];
        }
        int minIndex = start;
        int min = heights[start];
        for (int i=start;i<=end;i++){
            if (heights[i]<min){
                minIndex = i;
                min = heights[i];
            }
        }
        int left = div(heights, start, minIndex-1);
        int right = div(heights, minIndex+1, end);
        return Math.max(Math.max(left, right), min * (end-start+1));
    }


    private int[][] rmq(int[] nums){
        int n = (int) (Math.log(nums.length)/Math.log(2));
        int[][] dp = new int[nums.length][n+1];
        for (int i=0;i<nums.length;i++){
            dp[i][0] = nums[i];
        }
        for (int j=1;j<=n;j++){
            for (int i=0;i + (1<<(j-1)) < nums.length;i++){
                dp[i][j] = Math.min(dp[i][j-1], dp[i+(1<<(j-1))][j-1]);
            }
        }
        return dp;
    }


    private int[][] rmqIndex(int[] nums){
        int n = (int) (Math.log(nums.length)/Math.log(2));
        int[][] dp = new int[nums.length][n+1];
        for (int i=0;i<nums.length;i++){
            dp[i][0] = i;
        }
        for (int j=1;j<=n;j++){
            for (int i=0;i + (1<<(j-1)) < nums.length;i++){
                if (nums[dp[i][j-1]] < nums[dp[i+(1<<(j-1))][j-1]]){
                    dp[i][j] = dp[i][j-1];
                }else {
                    dp[i][j] = dp[i+(1<<(j-1))][j-1];
                }
            }
        }
        return dp;
    }

    private int rmqMin(int[][] rmq, int start, int end){
        int k =(int) (Math.log(end - start + 1) / Math.log(2));
        int rangeMin = Math.min(rmq[start][k], rmq[end+1-(1<<k)][k]);
        return rangeMin;
    }


    public int largestRectangleArea3(int[] heights) {
        if (heights.length==0){
            return 0;
        }
        int[][] rmq = rmq(heights);
        int[][] rmqIndex = rmqIndex(heights);
        return div2(heights, 0, heights.length-1, rmq, rmqIndex);
    }


    /**
     * Wrong
     * @param heights
     * @param start
     * @param end
     * @param rmq
     * @return
     */
    private int div2(int[] heights, int start, int end, int[][] rmq, int[][] rmqIndex){
        if (start>end){
            return 0;
        }
        if (start==end){
            return heights[start];
        }
        int k =(int) (Math.log(end - start + 1) / Math.log(2));
        int rangeMin = Math.min(rmq[start][k], rmq[end+1-(1<<k)][k]);
        int minIndex = rmqIndex[start][end];
        int left = div2(heights, start, minIndex-1, rmq, rmqIndex);
        int right = div2(heights, minIndex+1, end, rmq, rmqIndex);
        int midArea = rangeMin * (end-start+1);
        return Math.max(Math.max(left, right), midArea);
    }
}
