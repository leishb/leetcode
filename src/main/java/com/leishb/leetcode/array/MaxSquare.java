package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2018/1/18.
 */
public class MaxSquare {


    @Test
    public void test(){
        char[][] matrix = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        Assert.assertTrue(maximalSquare(matrix) == 4);
        Assert.assertTrue(maximalSquare(new char[][]{{'1', '1'}}) == 1);
    }



    public int maximalSquare(char[][] matrix) {
        int[] height = new int[matrix[0].length];
        int max = 0;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j] == '1'){
                    height[j] += 1;
                }else {
                    height[j] = 0;
                }
            }
            max = Math.max(max, maxArea(height));
        }
        return max;
    }




    private int maxArea(int[] nums){
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = 0;
        int l = 0;
        for(int i=1;i<nums.length;i++){
            l = i-1;
            while(l >=0 && nums[i] <= nums[l]){
                l = left[l] -1;
            }
            left[i] = l+1;
        }
        right[nums.length-1] = nums.length-1;
        int r = nums.length-1;
        for(int i=nums.length-2; i>=0; i--){
            r = i+1;
            while(r < nums.length && nums[i] <=nums[r]){
                r = right[r] + 1;
            }
            right[i] = r-1;
        }

        int max = 0;
        for(int i=0;i< nums.length;i++){
            if(right[i]-left[i]+1 >= nums[i]){
                max = Math.max(max, nums[i] * nums[i]);
            }else{
                max = Math.max(max, 1);
            }
        }
        return max;
    }
}
