package com.leishb.other;

import java.util.Arrays;

/**
 * https://leetcode.com/discuss/interview-question/477890/Google-or-Onsite-or-Zero-Sub-matrices-or-SWE-or-Round-4-(Technical)
 * Created by me on 2020/1/16.
 */
public class Number_of_submatrices_with_all_1s {



    public int matrixAllOne(int[][] matrix){
        int m = matrix.length, n = matrix[0].length;
        int[] arr = new int[m];
        int res = 0;
        for (int i=0;i<n;i++){
            Arrays.fill(arr, 0);
            for (int j=i;j<n;j++){
                for (int k=0;k<m;k++){
                    arr[k] += matrix[k][j];
                }
                res += countOfSum(arr, j-i+1);
            }
        }
        return res;
    }


    private int countOfSum(int[] arr, int cols){
        int count = 0, res = 0;
        for (int i=0;i<arr.length;i++){
            if (arr[i] == cols){
                count++;
                res+= count;
            }else {
                count=0;
            }
        }
        return res;
    }
}
