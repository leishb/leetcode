package com.leishb.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2017/11/2.
 */
public class SetMatrixZeros {




    /**
     * Accepted
     * @param matrix
     */
    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (matrix[i][j]==0){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if (rows.contains(i)){
                    matrix[i][j]=0;
                }
                if (cols.contains(j)){
                    matrix[i][j]=0;
                }
            }
        }
    }


    /**
     * Accepted
     * @param matrix
     */
    public void setZeroes2(int[][] matrix) {
        boolean firstRow = false;
        boolean firstCol = false;
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
                if (i==0 && matrix[i][j]==0){
                    firstRow=true;
                }
                if (j==0 && matrix[i][j]==0){
                    firstCol = true;
                }
            }
        }

        for(int i=1;i<matrix.length;i++){
            for(int j=1;j<matrix[0].length;j++){
                if(matrix[0][j]==0 || matrix[i][0]==0){
                    matrix[i][j]=0;
                }
            }
        }
        for (int i=0;i<matrix[0].length;i++){
            if (firstRow){
                matrix[0][i]=0;
            }
        }
        for (int i=0;i<matrix.length;i++){
            if (firstCol){
                matrix[i][0]=0;
            }
        }
    }
}
