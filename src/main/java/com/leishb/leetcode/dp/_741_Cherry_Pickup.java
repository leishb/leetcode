package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/11/22.
 */
public class _741_Cherry_Pickup {


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][][] memo = new int[n][n][n];
        for (int[][] m : memo){
            for (int[] arr : m) Arrays.fill(arr, Integer.MIN_VALUE);
        }
        return Math.max(0, dp(0, 0, 0, grid, memo));
    }


    private int dp(int r1, int c1, int r2,int[][] grid ,int[][][] memo ){
        int n = grid.length;
        int c2 = r1 + c1 - r2;
        if (r1==n || c1==n || r2==n || c2==n || grid[r1][c1]==-1 || grid[r2][c2]==-1) return -99999;
        if (r1==n-1 && c1==n-1){
            return grid[r1][c1];
        }else if (memo[r1][c1][r2]!=Integer.MIN_VALUE){
            return memo[r1][c1][r2];
        }else {
            int ans = grid[r1][c1];
            if (c1!=c2) ans += grid[r2][c2];
            ans += Math.max(Math.max(dp(r1+1, c1, r2, grid, memo), dp(r1, c1+1, r2, grid, memo)),
                    Math.max(dp(r1+1, c1, r2+1, grid, memo), dp(r1, c1+1, r2+1, grid, memo)));
            memo[r1][c1][r2] = ans;
            return ans;
        }
    }



    @Test
    public void test(){
        Assert.assertEquals(5, cherryPickup(new int[][]{{0,1,-1},{1,0,-1},{1,1,1}}));
        Assert.assertEquals(0, cherryPickup(new int[][]{{1,1,-1},{1,-1,1},{-1,1,1}}));
        Assert.assertEquals(15, cherryPickup(new int[][]
                {{1,1,1,1,0,0,0}
                ,{0,0,0,1,0,0,0}
                ,{0,0,0,1,0,0,1}
                ,{1,0,0,1,0,0,0}
                ,{0,0,0,1,0,0,0}
                ,{0,0,0,1,0,0,0}
                ,{0,0,0,1,1,1,1}}));
    }
}
