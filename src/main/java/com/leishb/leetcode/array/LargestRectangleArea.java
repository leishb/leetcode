package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.DivideAndConquer;
import com.leishb.leetcode.tag.DynamicProgramming;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by me on 2017/11/6.
 */
@DivideAndConquer
@DynamicProgramming
public class LargestRectangleArea {


    @Test
    public void test(){
        Assert.assertTrue(largestRectangleArea(new int[]{2,1,5,6,2,3})==10);
        Assert.assertTrue(largestRectangleArea2(new int[]{2,1,5,6,2,3})==10);
        Assert.assertTrue(largestRectangleArea2(new int[]{2,1,5})==5);
        Assert.assertTrue(largestRectangleArea2(new int[]{2,1,5,8,10,7})==21);
        Assert.assertTrue(largestRectangleArea2(new int[]{2,3})==4);
        Assert.assertTrue(largestRectangleArea2(new int[]{1,1})==2);



        Assert.assertTrue(largestRectangleArea4(new int[]{2,1,5,6,2,3})==10);
        Assert.assertTrue(largestRectangleArea4(new int[]{2,1,5})==5);
        Assert.assertTrue(largestRectangleArea4(new int[]{2,1,5,8,10,7})==21);
        Assert.assertTrue(largestRectangleArea4(new int[]{2,3})==4);
        Assert.assertTrue(largestRectangleArea4(new int[]{1,1})==2);


        Assert.assertTrue(largestRectangleArea5(new int[]{2,1,5,6,2,3})==10);
        Assert.assertTrue(largestRectangleArea5(new int[]{1,2,3,4})==6);
        Assert.assertTrue(largestRectangleArea5(new int[]{2,1,5})==5);
        Assert.assertTrue(largestRectangleArea5(new int[]{2,1,5,8,10,7})==21);
        Assert.assertTrue(largestRectangleArea5(new int[]{2,3})==4);
        Assert.assertTrue(largestRectangleArea5(new int[]{1,1})==2);
        Assert.assertTrue(largestRectangleArea5(new int[]{5})==5);
    }


    /**
     * Time limit exceeded
     * @param heights
     * @return
     */
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
        return div3(heights, 0, heights.length-1);
    }


    /**
     * Time limit exceeded
     * @param heights
     * @param start
     * @param end
     * @return
     */
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


    /**
     * Accepted
     * @param heights
     * @param start
     * @param end
     * @return
     */
    private int div3(int[] heights, int start, int end){
        if (start>end){
            return 0;
        }
        if (start==end){
            return heights[start];
        }
        int mid = (start+end)/2;
        int left = div3(heights, start, mid);
        int right = div3(heights, mid+1, end);

        int i = mid;
        int j = mid+1;
        int h = Math.min(heights[i], heights[j]);
        int midArea = 0;
        while(i>=start && j<=end){
            h = Math.min(h, Math.min(heights[i], heights[j]));
            midArea = Math.max(midArea, h * (j-i+1));
            if (i==start){
                j++;
            }else if (j==end){
                i--;
            }else {
                if (heights[i-1] > heights[j+1]){
                    i--;
                }else {
                    j++;
                }
            }
        }
        return Math.max(Math.max(left, right), midArea);
    }


    /**
     * Accepted
     * @param heights
     * @return
     */
    public int largestRectangleArea4(int[] heights) {
        if (heights.length==0){
            return 0;
        }
        int[] left = new int[heights.length];
        int[] right = new int[heights.length];
        left[0]=0;
        for (int i=1;i<heights.length;i++){
            int p = i-1;
            while (p>=0 && heights[p] >= heights[i]){
                p = left[p]-1;
            }
            left[i]=p+1;
        }
        right[heights.length-1]=heights.length-1;
        for (int i=heights.length-2;i>=0;i--){
            int p = i+1;
            while (p<=heights.length-1 && heights[p]>=heights[i]){
                p = right[p] + 1;
            }
            right[i]=p-1;
        }
        int maxArea = 0;
        for (int i=0;i<heights.length;i++){
            maxArea = Math.max(maxArea, heights[i] * (right[i]-left[i]+1));
        }
        return maxArea;
    }


    /**
     * Accepted
     * @param heights
     * @return
     */
    public int largestRectangleArea5(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        for (int i=0;i<heights.length;){
            if (stack.isEmpty() || heights[i]>=heights[stack.peek()]){
                stack.push(i);
                i++;
            }else {
                int h=heights[stack.pop()];
                int r= i-1;
                int l=stack.isEmpty() ? 0 : stack.peek()+1;
                maxArea = Math.max(maxArea, h * (r-l+1));
            }
        }
        while (!stack.isEmpty()){
            int h=heights[stack.pop()];
            int r= heights.length-1;
            int l=stack.isEmpty() ? 0 : stack.peek()+1;
            maxArea = Math.max(maxArea, h * (r-l+1));
        }
        return maxArea;
    }



    public int largestRectangleArea6(int[] height){
        int len = height.length;
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i = 0; i <= len; i++){
            int h = (i == len ? 0 : height[i]);
            if(s.isEmpty() || h >= height[s.peek()]){
                s.push(i);
            }else{
                int tp = s.pop();
                maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                i--;
            }
        }
        return maxArea;
    }







    public int largestRectangleArea3(int[] heights) {
        if (heights.length==0){
            return 0;
        }
        int[][] rmq = rmq(heights);
        int[][] rmqIndex = rmqIndex(heights);
        return div2(heights, 0, heights.length-1, rmq, rmqIndex);
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
