package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/30.
 */
public class _915_Partition_Array_into_Disjoint_Intervals {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int partitionDisjoint(int[] A) {
        int[] leftMax = new int[A.length];
        leftMax[0] = A[0];
        for (int i=1;i<A.length;i++){
            leftMax[i] = Math.max(leftMax[i-1], A[i]);
        }
        int[] rightMin = new int[A.length];
        rightMin[A.length-1] = A[A.length-1];
        for (int i=A.length-2;i>=0;i--){
            rightMin[i] = Math.min(rightMin[i+1], A[i]);
        }
        for (int i=0;i<A.length-1;i++){
            if (leftMax[i]<=rightMin[i+1]){
                return i+1;
            }

        }
        return 0;
    }


    @Test
    public void test(){
        Assert.assertTrue(partitionDisjoint(new int[]{5,0,3,8,6})==3);
        Assert.assertTrue(partitionDisjoint(new int[]{1,1,1,0,6,12})==4);
        Assert.assertTrue(partitionDisjoint(new int[]{1,1,1,0,1,6,12})==4);
    }
}
