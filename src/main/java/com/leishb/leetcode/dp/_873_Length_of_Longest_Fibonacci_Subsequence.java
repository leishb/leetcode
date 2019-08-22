package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/8/21.
 */
public class _873_Length_of_Longest_Fibonacci_Subsequence {

    /**
     * Accepted
     * @param A
     * @return
     */
    public int lenLongestFibSubseq(int[] A) {
        int[][] dp = new int[A.length][A.length];
        int max = 0;
        for (int i=2;i<A.length;i++){
            for (int j=i-1;j>0;j--){
                int k = search(A, 0, j-1, A[i]-A[j]);
                if (k==-1){
                    dp[i][j] = 0;
                }else {
                    dp[i][j] = dp[j][k]+1;
                }
                max = Math.max(dp[i][j], max);
            }
        }
        return max+2;
    }


    private int search(int[] arr, int start, int end, int target){
        while (start<=end){
            int mid = (start+end)/2;
            if (arr[mid]==target){
                return mid;
            }
            if (arr[mid]>target){
                end = mid-1;
            }else {
                start = mid+1;
            }
        }
        return -1;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int lenLongestFibSubseq2(int[] A) {
        Set<Integer> set = new HashSet<>();
        for (int num : A){
            set.add(num);
        }
        int max = 0;
        for (int i=0;i<A.length;i++){
            for (int j=i+1;j<A.length;j++){
                int x = A[i], y = A[j];
                int len = 2;
                while (set.contains(x+y)){
                    int temp = x;
                    x = y;
                    y = temp+y;
                    len++;
                }
                max = Math.max(max, len);
            }
        }
        return max>2?max:0;
    }

    @Test
    public void test(){
        Assert.assertTrue(lenLongestFibSubseq2(new int[]{1,2,3,4,5,6,7,8})==5);
        Assert.assertTrue(lenLongestFibSubseq2(new int[]{1,3,7,11,12,14,18})==3);
        Assert.assertTrue(lenLongestFibSubseq2(new int[]{1,3,5})==0);
    }
}
