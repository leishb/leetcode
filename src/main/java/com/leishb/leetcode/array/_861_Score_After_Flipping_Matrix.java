package com.leishb.leetcode.array;

/**
 * Created by me on 2019/8/21.
 */
public class _861_Score_After_Flipping_Matrix {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int matrixScore(int[][] A) {
        int[] colZeroCounts = new int[A[0].length];
        for (int i=0;i<A.length;i++){
            boolean zeroStart = A[i][0] == 0;
            for (int j=0;j<A[i].length;j++){
                if (zeroStart){
                    A[i][j] = A[i][j]^1;
                }
                if (A[i][j]==0){
                    colZeroCounts[j] +=1;
                }
            }
        }
        int ans = 0;
        for (int i=0;i<A.length;i++){
            for (int j=0;j<A[i].length;j++){
                if (colZeroCounts[j] *2 > A.length){
                    A[i][j] = A[i][j]^1;
                }
                if (A[i][j]==1){
                    ans += (int) Math.pow(2, A[i].length-j-1);
                }
            }
        }
        return ans;
    }
}
