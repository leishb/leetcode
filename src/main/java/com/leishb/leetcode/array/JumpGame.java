package com.leishb.leetcode.array;

import com.leishb.leetcode.tag.Greedy;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2017/10/30.
 */
@Greedy
public class JumpGame {

    @Test
    public void test(){
        Assert.assertTrue(jump(new int[]{2,1,3,4,6})==2);
        Assert.assertTrue(jump(new int[]{2,4,3,4,6,8})==2);
        Assert.assertTrue(jump(new int[]{2,4,3,4,6,8,1,3,2})==3);
        Assert.assertTrue(jump(new int[]{1,1,1,1,1})==4);
        Assert.assertTrue(jump(new int[]{2})==0);
        Assert.assertTrue(jump(new int[]{4,1,1,1})==1);
        Assert.assertTrue(jump(new int[]{3,6,1,2,1})==2);
        Assert.assertTrue(jump(new int[]{3,2,0,1,1})==2);
        Assert.assertTrue(jump(new int[]{2,3,0,1,4})==2);
        Assert.assertTrue(jump(new int[]{3,4,3,2,5,4,3})==3);



        Assert.assertTrue(jump2(new int[]{2,1,3,4,6})==2);
        Assert.assertTrue(jump2(new int[]{2,4,3,4,6,8})==2);
        Assert.assertTrue(jump2(new int[]{2,4,3,4,6,8,1,3,2})==3);
        Assert.assertTrue(jump2(new int[]{1,1,1,1,1})==4);
        Assert.assertTrue(jump2(new int[]{2})==0);
        Assert.assertTrue(jump2(new int[]{4,1,1,1})==1);
        Assert.assertTrue(jump2(new int[]{3,6,1,2,1})==2);
        Assert.assertTrue(jump2(new int[]{3,2,0,1,1})==2);
        Assert.assertTrue(jump2(new int[]{2,3,0,1,4})==2);
        Assert.assertTrue(jump2(new int[]{3,4,3,2,5,4,3})==3);
        Assert.assertTrue(jump2(new int[]{1,2,0,0,0,0})==0);


        Assert.assertTrue(canJump(new int[]{2,1,3,4,6}));
        Assert.assertTrue(canJump(new int[]{2,4,3,4,6,8}));
        Assert.assertTrue(canJump(new int[]{2,4,3,4,6,8,1,3,2}));
        Assert.assertTrue(canJump(new int[]{1,1,1,1,1}));
        Assert.assertTrue(canJump(new int[]{2}));
        Assert.assertTrue(canJump(new int[]{4,1,1,1}));
        Assert.assertTrue(canJump(new int[]{3,6,1,2,1}));
        Assert.assertTrue(canJump(new int[]{3,2,0,1,1}));
        Assert.assertTrue(canJump(new int[]{2,3,0,1,4}));
        Assert.assertTrue(canJump(new int[]{3,4,3,2,5,4,3}));
        Assert.assertTrue(!canJump(new int[]{1,2,0,0,0,0}));
        Assert.assertTrue(!canJump(new int[]{3,2,1,0,4}));
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int jump(int[] nums){
        int start=0;
        int endIndex =start;
        int count=0;
        while (endIndex<nums.length-1){
            int max=0;
            int maxIndex=0;
            if (endIndex+nums[endIndex] >=nums.length-1){
                count++;
                break;
            }
            for (int i=start;i<=endIndex+nums[endIndex];i++){
                if (i+nums[i]>max){
                    max=i+nums[i];
                    maxIndex=i;
                }
            }
            count++;
            start = start + nums[start];
            endIndex=maxIndex;
        }
        return count;
    }


    /**
     * Accepted
     * @param nums
     * @return
     */
    public int jump2(int[] nums){
        if (nums.length<2){
            return 0;
        }
        int i=0;
        int endIndex=0;
        int max=0;
        int count=0;
        while (endIndex-i+1>0){
            for (;i<=endIndex;i++){
                max=Math.max(i+nums[i],max);
            }
            if (max>=nums.length-1){
                count++;
                return count;
            }
            count++;
            endIndex=max;
        }
        return 0;
    }


    public boolean canJump(int[] nums) {
        if (nums.length<2){
            return true;
        }
        int i=0;
        int endIndex=0;
        int max =0;
        while (endIndex-i+1>0) {
            for (;i<=endIndex;i++){
               max = Math.max(max, i+nums[i]);
            }
            if (max>=nums.length-1){
                return true;
            }
            endIndex=max;
        }
        return false;
    }
}
