package com.leishb.leetcode.stack;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/8/29.
 */
public class _907_Sum_of_Subarray_Minimums {


    int M = 1000000007;

    public int sumSubarrayMins(int[] A) {
        int sum = 0;
        for (int i=0;i<A.length;i++){
            int min = A[i];
            for (int j=i;j<A.length;j++){
                min = Math.min(min, A[j]);
                sum=(sum+min)%M;
            }
        }
        return sum;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int sumSubarrayMins2(int[] A) {
        int[] left = new int[A.length];
        left[0] = 0;
        for (int i=1;i<A.length;i++){
            int k = i-1;
            while (k>=0 &&A[i]<=A[k]){
                k=left[k]-1;
            }
            left[i] = k+1;
        }
        int[] right = new int[A.length];
        right[A.length-1] = A.length-1;
        for (int i=A.length-2;i>=0;i--){
            int k = i+1;
            while (k<A.length && A[i]<A[k]){
                k = right[k]+1;
            }
            right[i] = k-1;
        }
        int sum = 0;
        for (int i=0;i<A.length;i++){
            sum = (sum+((i-left[i])*(right[i]-i)%M+right[i]-left[i]+1)*A[i]%M)%M;
        }
        return sum;
    }
    @Test
    public void test(){
        Assert.assertTrue(sumSubarrayMins2(new int[]{3,1,2,4})==17);
        Assert.assertTrue(sumSubarrayMins2(new int[]{71,55,82,55})==593);
        Assert.assertTrue(sumSubarrayMins2(new int[]{5,6,7,8,9,10,6,7,8})==sumSubarrayMins(new int[]{5,6,7,8,9,10,6,7,8}));
        Assert.assertTrue(2*3%2==0);
    }
}
