package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/8/14.
 */
public class _801_Minimum_Swaps_To_Make_Sequences_Increasing {

    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int minSwap(int[] A, int[] B) {
        int[][] dp = new int[A.length][2];
        dp[0][1] = 1;
        for (int i=1;i<A.length;i++){
            boolean selfIncreasing = A[i]>A[i-1] && B[i]>B[i-1];
            boolean crossIncreasing = A[i]>B[i-1] && B[i]>A[i-1];
            if (selfIncreasing && crossIncreasing){
                dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]);
                dp[i][1] = dp[i][0]+1;
            }else if (selfIncreasing){
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1]+1;
            }else {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = dp[i-1][0]+1;
            }
        }
        return Math.min(dp[A.length-1][0] , dp[A.length-1][1]);
    }


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int minSwap2(int[] A, int[] B) {
        int swapNo = 0;
        int swapYes = 1;
        for (int i=1;i<A.length;i++){
            boolean selfIncreasing = A[i]>A[i-1] && B[i]>B[i-1];
            boolean crossIncreasing = A[i]>B[i-1] && B[i]>A[i-1];
            if (selfIncreasing && crossIncreasing){
                swapNo = Math.min(swapYes, swapNo);
                swapYes = swapNo+1;
            }else if (selfIncreasing){
                swapYes +=1;
            }else {
                int temp = swapNo;
                swapNo = swapYes;
                swapYes = temp+1;
            }
        }
        return Math.min(swapYes, swapNo);
    }
}
