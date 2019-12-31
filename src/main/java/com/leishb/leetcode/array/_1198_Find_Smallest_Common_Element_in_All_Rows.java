package com.leishb.leetcode.array;

/**
 * Created by me on 2019/11/25.
 */
public class _1198_Find_Smallest_Common_Element_in_All_Rows {


    public int smallestCommonElement(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        for (int i=0;i<n;i++){
            int k = mat[0][i];
            int j = 1;
            for (;j<m;j++){
                if (!exist(mat[j], k)){
                    break;
                }
            }
            if(j==m) return k;
        }
        return -1;
    }


    private boolean exist(int[] arr, int k){
        int i=0, j= arr.length-1;
        while (i<=j){
            int m = (i+j)/2;
            if (arr[m]==k) return true;
            if (arr[m] > k){
                j = m-1;
            }else {
                i = m + 1;
            }
        }
        return false;
    }
}
