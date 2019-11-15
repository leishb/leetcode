package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/11/13.
 */
public class _668_Kth_Smallest_Number_in_Multiplication_Table {


    /**
     * Accepted
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int m, int n, int k) {
        int lo =1, hi = m*n;
        while (lo<=hi){
            int mid = (hi-lo)/2+lo;
            int less = getLessEqual(m, n, mid);
            if (less<k){
                lo = mid +1;
            }else {
                hi = mid -1;
            }
        }
        return lo;
    }


    private int getLessEqual(int m, int n, int val){
        int i = 1, j= n, count = 0;
        while (i<=m && j>=1){
            if (i*j > val){
                j--;
            }else {
                count += j;
                i++;
            }
        }
        return count;
    }


    @Test
    public void test(){
        findKthNumber(300, 200, 200);
    }
}
