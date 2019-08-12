package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/30.
 */
@TwoPointers
public class TrapRainWater {


    @Test
    public void test(){
        Assert.assertTrue(trap2(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})==6);
        Assert.assertTrue(trap2(new int[]{4,1,2,3,4})==6);
        Assert.assertTrue(trap2(new int[]{0,1,2,3,4})==0);
        Assert.assertTrue(trap2(new int[]{4,1,2,3,4,4,4})==6);
        Assert.assertTrue(trap2(new int[]{4,1,2,3,4,1,4})==9);
        Assert.assertTrue(trap2(new int[]{4,1,2,3,2})==3);
        Assert.assertTrue(trap2(new int[]{2,0,2})==2);
        Assert.assertTrue(trap2(new int[]{2,0})==0);
        Assert.assertTrue(trap2(new int[]{0})==0);
        Assert.assertTrue(trap2(new int[]{5,2,1,2,1,5})==14);
    }


    /**
     * Accepted
     * @param height
     * @return
     */
    public int trap(int[] height){
        int leftIndex=0;
        int rightIndex=height.length-1;
        int result =0;
        while (leftIndex<rightIndex && height[leftIndex]<=height[leftIndex+1]){
            leftIndex++;
        }
        while (leftIndex<rightIndex && height[rightIndex]<=height[rightIndex-1]){
            rightIndex--;
        }
        while (leftIndex < rightIndex){
            int leftValue = height[leftIndex];
            int rightValue = height[rightIndex];

            if (leftValue < rightValue){
                while (leftIndex < rightIndex && leftValue>=height[leftIndex]){
                    result += leftValue-height[leftIndex];
                    leftIndex++;
                }
            }else {
                while (leftIndex<rightIndex && rightValue >= height[rightIndex]){
                    result += rightValue-height[rightIndex];
                    rightIndex--;
                }
            }
        }
        return result;
    }


    /**
     * Accepted
     * @param height
     * @return
     */
    public int trap2(int[] height) {
        int n = height.length;
        int[] left = new int[height.length];
        int[] right = new int[height.length];
        left[0] = height[0];
        for (int i=1;i<n;i++){
            left[i] = Math.max(height[i], left[i-1]);
        }
        right[n-1] = height[n-1];
        for (int i=n-2;i>=0;i--){
            right[i] = Math.max(right[i+1], height[i]);
        }
        int ans = 0;
        for (int i=0;i<n;i++){
            ans += Math.min(left[i], right[i]) - height[i];
        }
        return ans;
    }
}
