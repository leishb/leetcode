package com.leishb.leetcode.array;

/**
 * Created by me on 2019/10/9.
 */
public class _498_Diagonal_Traverse {



    public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix.length==0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] ans = new int[m*n];
        boolean up = true;
        int k = 0;
        for (int p=0;p<=m+n-2;p++){
            if (up){
                for (int i=(p>=m?m-1:p);i>=0 && p-i<n;i--){
                    ans[k++] = matrix[i][p-i];
                }
            }else {
                for (int i=(p>=n?n-1:p);i>=0 && p-i<m;i--){
                    ans[k++] = matrix[p-i][i];
                }
            }
            up = !up;
        }
        return ans;
    }


    public int[] findDiagonalOrder2(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        int[] ans = new int[m * n];

        int[][] dirs = new int[][]{{-1,1},{1,-1}};
        int row = 0, col = 0, d=0;
        for (int i=0;i<m*n;i++){
            ans[i] = matrix[row][col];
            row += dirs[d][0];
            col += dirs[d][1];
            if (row>=m){
                row = m-1;
                col +=2;
                d = 1-d;
            }
            if (col>=n){
                col = n-1;
                row +=2;
                d = 1-d;
            }
            if (row<0){
                row = 0;
                d = 1-d;
            }
            if (col<0){
                col = 0;
                d = 1-d;
            }
        }
        return ans;
    }
}
