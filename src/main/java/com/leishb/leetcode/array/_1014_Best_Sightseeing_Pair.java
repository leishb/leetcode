package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/10.
 */
public class _1014_Best_Sightseeing_Pair {

    public int maxScoreSightseeingPair(int[] A) {
        int[] leftMax = new int[A.length];
        leftMax[0] = A[0]+0;
        for (int i=1;i<A.length;i++){
            leftMax[i] = Math.max(leftMax[i-1], A[i]+i);
        }
        int[] rightMax = new int[A.length];
        rightMax[A.length-1] = A[A.length-1]-(A.length-1);
        for (int i=A.length-2;i>=0;i--){
            rightMax[i] = Math.max(rightMax[i+1], A[i]-i);
        }
        int ans = 0;
        for (int i=0;i<A.length-1;i++){
            ans = Math.max(leftMax[i]+rightMax[i+1], ans);
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(maxScoreSightseeingPair(new int[]{8,1,5,2,6})==11);
    }
}
