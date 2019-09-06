package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/9/4.
 */
public class _945_Minimum_Increment_to_Make_Array_Unique {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int minIncrementForUnique(int[] A) {
        int sum = 0;
        int afterSum = 0;
        Arrays.sort(A);
        for (int i=0;i<A.length;i++){
            sum += A[i];
            if (i>0 && A[i]<=A[i-1]){
                A[i] = A[i-1]+1;
            }
            afterSum+=A[i];
        }
        return afterSum-sum;
    }


    @Test
    public void test(){
        Assert.assertTrue(minIncrementForUnique(new int[]{3,2,1,2,1,7})==6);
        Assert.assertTrue(minIncrementForUnique(new int[]{1,2,2})==1);
        Assert.assertTrue(minIncrementForUnique(new int[]{1,2})==0);
        Assert.assertTrue(minIncrementForUnique(new int[]{0,2,2})==1);
        Assert.assertTrue(minIncrementForUnique(new int[]{})==0);
    }
}
