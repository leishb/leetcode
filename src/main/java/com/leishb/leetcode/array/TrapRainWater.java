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
        Assert.assertTrue(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1})==6);
        Assert.assertTrue(trap(new int[]{4,1,2,3,4})==6);
        Assert.assertTrue(trap(new int[]{0,1,2,3,4})==0);
        Assert.assertTrue(trap(new int[]{4,1,2,3,4,4,4})==6);
        Assert.assertTrue(trap(new int[]{4,1,2,3,4,1,4})==9);
        Assert.assertTrue(trap(new int[]{4,1,2,3,2})==3);
        Assert.assertTrue(trap(new int[]{2,0,2})==2);
        Assert.assertTrue(trap(new int[]{2,0})==0);
        Assert.assertTrue(trap(new int[]{0})==0);
        Assert.assertTrue(trap(new int[]{5,2,1,2,1,5})==14);
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
}
