package com.leishb.leetcode.array;

/**
 * Created by me on 2019/9/11.
 */
public class _1031_Maximum_Sum_of_Two_Non_Overlapping_Subarrays {

    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int[] sums = new int[A.length+1];
        for (int i=1;i<=A.length;i++){
            sums[i] = sums[i-1] + A[i-1];
        }
        return Math.max(maxSumTwoNoOverlap(A, L, M, sums), maxSumTwoNoOverlap(A, M, L, sums));
    }

    public int maxSumTwoNoOverlap(int[] A, int L, int M, int[] sums){
        int[] left = new int[A.length];
        left[L-1] = sums[L];
        for (int i=L;i<A.length;i++){
            left[i] = Math.max(left[i-1], sums[i+1]-sums[i-L+1]);
        }
        int[] right = new int[A.length];
        right[A.length-M] = sums[A.length] - sums[A.length-M];
        for (int i= A.length-M-1;i>=0;i--){
            right[i] = Math.max(right[i+1], sums[M+i]-sums[i]);
        }
        int ans = 0;
        for (int i=L-1;i<A.length-M;i++){
            ans = Math.max(ans, left[i]+right[i+1]);
        }
        return ans;
    }
}
