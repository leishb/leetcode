package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/12/26.
 */
public class _982_Triples_with_Bitwise_AND_Equal_To_Zero {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int countTriplets(int[] A) {
        int N = 1 << 16, M = 3;
        int[][] dp = new int[M+1][N];
        for (int a : A){
            dp[1][a] += 1;
        }
        for (int i=2;i<=M;i++){
            for (int j=0;j<N;j++){
                for (int a : A){
                    dp[i][j & a] += dp[i-1][j];
                }
            }
        }
        return dp[3][0];
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int countTriplets2(int[] A) {
        int[] count = new int[1<<16];
        for (int a : A){
            for (int b : A){
                count[a & b] +=1;
            }
        }
        int res = 0;
        for (int i=0;i<count.length;i++){
            for (int a : A){
                if ((a & i) ==0){
                    res += count[i];
                }
            }
        }
        return res;
    }
}
