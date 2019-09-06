package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/3.
 */
public class _935_Knight_Dialer {

    int MOD = 1_000_000_007;

    int[][] dirs = new int[][]{{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{-2,1},{2,-1},{-2,-1}};

    /**
     * Accepted
     * @param N
     * @return
     */
    public int knightDialer(int N) {
        int[][] boards = new int[][]{{1,2,3},{4,5,6},{7,8,9},{-1,0,-1}};
        long[][] dp = new long[10][N+1];
        for (int i=0;i<10;i++){
            dp[i][1] = 1L;
        }
        for (int k=2;k<=N;k++){
            for (int i=0;i<boards.length;i++){
                for (int j=0;j<boards[0].length;j++){
                    if (boards[i][j]==-1){
                        continue;
                    }
                    for (int[] dir : dirs){
                        int x = i+dir[0];
                        int y = j+dir[1];
                        if (x>=0 && x<=3 && y>=0 && y<=2 && boards[x][y]!=-1){
                            int num = boards[x][y];
                            dp[boards[i][j]][k] = (dp[boards[i][j]][k] + dp[num][k-1])%MOD;
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int i=0;i<10;i++){
            ans = (int) ((ans + dp[i][N]%MOD)%MOD);
        }
        return ans;
    }


    @Test
    public void test(){
        Assert.assertTrue(knightDialer(1)==10);
        Assert.assertTrue(knightDialer(2)==20);
        Assert.assertTrue(knightDialer(3)==46);
        Assert.assertTrue(knightDialer(161)==533302150);
    }
}
