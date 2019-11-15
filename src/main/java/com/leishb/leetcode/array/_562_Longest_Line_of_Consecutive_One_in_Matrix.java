package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/11/13.
 */
public class _562_Longest_Line_of_Consecutive_One_in_Matrix {


    /**
     * Accepted
     * @param M
     * @return
     */
    public int longestLine(int[][] M) {
        if (M.length==0) return 0;
        int m = M.length, n = M[0].length;
        int max = 0;
        int[][][] dp = new int[m][n][4];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (M[i][j]==0) continue;
                for (int k=0;k<4;k++)dp[i][j][k] = 1;
                if (j>0) dp[i][j][0] += dp[i][j-1][0] ; //horizontal
                if (i>0) dp[i][j][1] += dp[i-1][j][1]; //vertical
                if (i>0 && j>0) dp[i][j][2] += dp[i-1][j-1][2] ;//diag
                if (i>0 && j<n-1) dp[i][j][3] += dp[i-1][j+1][3] ;//anti
                max = Math.max(max, Math.max(dp[i][j][0],dp[i][j][1]));
                max = Math.max(max, Math.max(dp[i][j][2],dp[i][j][3]));
            }
        }
        return max;
    }


    @Test
    public void test(){
        Assert.assertEquals(4,longestLine(new int[][]{{1,1,1,1},{0,1,1,0},{0,0,0,1}}));
    }
}
