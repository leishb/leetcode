package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/9.
 */
public class _769_Max_Chunks_To_Make_Sorted {


    /**
     * Accepted
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int chunks = 0;
        int lastIndex = -1;
        for (int i=0;i<arr.length;i++){
            if (arr[i] > i){
                lastIndex = Math.max(lastIndex, arr[i]);
            }else if (arr[i] == i){
                if (i>lastIndex){
                    chunks++;
                }
            }else {
                if (i==lastIndex){
                    chunks++;
                }
            }
        }
        return chunks;
    }

    public int maxChunksToSorted2(int[] arr) {
        if (arr.length==0)return 0;
        int chunks = 0;
        int[] max = new int[arr.length];
        max[0] = arr[0];
        for (int i=1;i<arr.length;i++){
            max[i] = Math.max(max[i-1], arr[i]);
        }
        for (int i=0;i<arr.length;i++){
            if (max[i] == i){
                chunks++;
            }
        }
        return chunks;
    }


    @Test
    public void test(){
        Assert.assertTrue(maxChunksToSorted2(new int[]{1,0,2,3,4})==4);
        Assert.assertTrue(maxChunksToSorted2(new int[]{1,3,0,2,4})==2);
        Assert.assertTrue(maxChunksToSorted2(new int[]{1,3,2,4,0})==1);
        Assert.assertTrue(maxChunksToSorted2(new int[]{1,0,3,4,2})==2);
        Assert.assertTrue(maxChunksToSorted2(new int[]{4,3,2,1,0})==1);
        Assert.assertTrue(maxChunksToSorted2(new int[]{0})==1);
        Assert.assertTrue(maxChunksToSorted2(new int[]{0,1})==2);
    }
}
