package com.leishb.leetcode.array;

/**
 * Created by me on 2020/1/15.
 */
public class _852_Peak_Index_in_a_Mountain_Array {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int peakIndexInMountainArray(int[] A) {
        int i = 0, j = A.length-1;
        while (i<=j){
            int m = (i+j)/2;
            if (A[m] < A[m+1]){
                i = m+1;
            }else if (A[m] < A[m-1]){
                j = m-1;
            }else {
                return m;
            }
        }
        return 0;
    }
}
