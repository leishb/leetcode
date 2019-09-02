package com.leishb.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/11/13.
 */
public class MaximalRectangle {

    @Test
    public void test(){
        Assert.assertTrue(maximalRectangle(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}})==6);
    }


    /**
     * Accepted
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length==0){
            return 0;
        }
        int max = 0;
        int[][] heights = new int[matrix.length][matrix[0].length];
        for (int i=0;i<matrix[0].length;i++){
            heights[0][i]=matrix[0][i]-'0';
        }
        for (int i=1;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (matrix[i][j]=='0'){
                    heights[i][j]=0;
                }else {
                    heights[i][j] = heights[i-1][j]+1;
                }
            }
        }

        for (int i=0;i<matrix.length;i++){
            max = Math.max(max, largestRectangleArea(heights, i));
        }
        return max;
    }

    public int largestRectangleArea(int[][] heights, int k) {
        if (heights.length==0){
            return 0;
        }
        int n = heights[0].length;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0]=0;
        for (int i=1;i<n;i++){
            int p = i-1;
            while (p>=0 && heights[k][p] >= heights[k][i]){
                p = left[p]-1;
            }
            left[i]=p+1;
        }
        right[n-1]=n -1;
        for (int i=n-2;i>=0;i--){
            int p = i+1;
            while (p<=n-1 && heights[k][p]>=heights[k][i]){
                p = right[p] + 1;
            }
            right[i]=p-1;
        }
        int maxArea = 0;
        for (int i=0;i<n;i++){
            maxArea = Math.max(maxArea, heights[k][i] * (right[i]-left[i]+1));
        }
        return maxArea;
    }
}
