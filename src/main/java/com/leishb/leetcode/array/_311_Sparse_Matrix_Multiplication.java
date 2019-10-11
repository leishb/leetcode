package com.leishb.leetcode.array;

/**
 * Created by me on 2019/9/27.
 */
public class _311_Sparse_Matrix_Multiplication {

    public int[][] multiply(int[][] A, int[][] B) {
        int am = A.length, an = A[0].length, bm = B.length, bn = B[0].length;
        int[][] ans = new int[am][bn];
        for (int i=0;i<am;i++){
            for (int j=0;j<bn;j++){
                int sum = 0;
                for (int k=0;k<an;k++){
                    sum += A[i][k] * B[k][j];
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
}
