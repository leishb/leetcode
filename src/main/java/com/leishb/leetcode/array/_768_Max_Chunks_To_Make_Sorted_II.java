package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/9.
 */
public class _768_Max_Chunks_To_Make_Sorted_II {


    /**
     * Accepted
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        if (arr.length==0)return 0;
        int[] maxOfLeft = new int[arr.length];
        int[] minOfRight = new int[arr.length];
        maxOfLeft[0] = arr[0];
        for(int i=1;i<arr.length;i++){
            maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
        }
        minOfRight[arr.length-1] = arr[arr.length-1];
        for (int i=arr.length-2;i>=0;i--){
            minOfRight[i] = Math.min(minOfRight[i+1], arr[i]);
        }
        int chunks = 0;
        for (int i=0;i<arr.length-1;i++){
            if (maxOfLeft[i]<=minOfRight[i+1])chunks++;
        }
        return chunks+1;
    }




    @Test
    public void test(){
        Assert.assertTrue(maxChunksToSorted(new int[]{1,0,2,3,4})==4);
        Assert.assertTrue(maxChunksToSorted(new int[]{1,3,0,2,4})==2);
        Assert.assertTrue(maxChunksToSorted(new int[]{1,3,2,4,0})==1);
        Assert.assertTrue(maxChunksToSorted(new int[]{1,0,3,4,2})==2);
        Assert.assertTrue(maxChunksToSorted(new int[]{4,3,2,1,0})==1);
        Assert.assertTrue(maxChunksToSorted(new int[]{0})==1);
        Assert.assertTrue(maxChunksToSorted(new int[]{0,1})==2);
    }
}
