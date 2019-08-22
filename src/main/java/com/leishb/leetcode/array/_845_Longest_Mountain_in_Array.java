package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/20.
 */
public class _845_Longest_Mountain_in_Array {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int longestMountain(int[] A) {
        if (A.length<=2) return 0;
        int[] left = new int[A.length];
        int[] right = new int[A.length];
        left[0] = 0;
        for (int i=1;i<left.length;i++){
            if (A[i] > A[i-1]){
                left[i] = left[i-1]+1;
            }else {
                left[i] = 0;
            }
        }
        right[right.length-1] = 0;
        for (int i=right.length-2;i>=0;i--){
            if (A[i] > A[i+1]){
                right[i] = right[i+1]+1;
            }else {
                right[i] = 0;
            }
        }
        int ans = 0;
        for (int i=0;i<A.length;i++){
            if (left[i]>0 && right[i]>0){
                ans = Math.max(ans, left[i]+right[i]+1);
            }
        }
        return ans;
    }

    public int longestMountain2(int[] A) {
        int up = 0;
        int down = 0;
        int ans = 0;
        for (int i=1;i<A.length;i++){
            if (A[i]>A[i-1]){ //up
                if (down==0){
                    up +=1;
                }else {
                    up = 1;
                    down = 0;
                }
            }else if (A[i]<A[i-1]){
                if (up > 0){
                    down +=1;
                    ans = Math.max(ans, up+down+1);
                }
            }else {
                up = 0;
                down = 0;
            }
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(longestMountain2(new int[]{2,1,4,7,3,2,5})==5);
        Assert.assertTrue(longestMountain2(new int[]{1,2,3,4,7,3,2,5})==7);
        Assert.assertTrue(longestMountain2(new int[]{2,2,2})==0);
        Assert.assertTrue(longestMountain2(new int[]{1,2,3,4,4 ,1})==0);
        Assert.assertTrue(longestMountain2(new int[]{1,2,3,4,4,5,3})==3);
        Assert.assertTrue(longestMountain2(new int[]{2,3,3,2,0,2})==0);
    }

}
