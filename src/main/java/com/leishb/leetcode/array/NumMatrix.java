package com.leishb.leetcode.array;

/**
 * Created by me on 2019/6/3.
 * https://leetcode.com/problems/range-sum-query-2d-immutable/
 * Accepted
 */
public class NumMatrix {


    int[][] sum;

    public NumMatrix(int[][] matrix) {
        sum = new int[matrix.length+1][matrix[0].length+1];
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                sum[i+1][j+1] = sum[i+1][j] + matrix[i][j];
            }
        }
        for (int i=1;i<sum.length;i++){
            for (int j=1;j<sum[0].length;j++){
                sum[i][j] += sum[i-1][j];
            }
        }

    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2+1][col2+1] - sum[row2+1][col1] - sum[row1][col2+1] + sum[row1][col1];
    }




    public static void main(String[] args){
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix.sumRegion(2,1,4,3));
    }
}
