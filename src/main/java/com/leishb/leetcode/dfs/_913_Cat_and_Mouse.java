package com.leishb.leetcode.dfs;

import java.util.Arrays;

/**
 * Created by me on 2019/12/18.
 */
public class _913_Cat_and_Mouse {


    /**
     * Accepted
     * @param graph
     * @return
     */
    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        int[][][] dp = new int[2*n][n][n];
        for(int i=0;i<2*n;i++){
            for (int j=0;j<n;j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        return dfs(graph, dp, 0, 1, 2);
    }


    private int dfs(int[][] graph, int[][][] dp, int steps, int x, int y){
        if (steps==2*graph.length) return 0;
        if (x==y) return 2;
        if (x==0) return 1;
        if (dp[steps][x][y]!=-1) return dp[steps][x][y];
        boolean mouseTurn = steps%2==0;
        if (mouseTurn){
            boolean draw = false;
            for (int next : graph[x]){
                int mouseWin = dfs(graph, dp, steps+1, next, y);
                draw |= mouseWin==0;
                if (mouseWin==1){
                    dp[steps][x][y] = 1;
                    return 1;
                }
            }
            dp[steps][x][y] = draw?0:2;
            return dp[steps][x][y];
        }
        boolean draw = false;
        for (int next : graph[y]){
            if (next==0) continue;
            int catWin = dfs(graph, dp, steps+1, x, next);
            draw |= catWin==0;
            if (catWin==2){
                dp[steps][x][y] = 2;
                return 2;
            }
        }
        dp[steps][x][y] = draw?0:1;
        return dp[steps][x][y];
    }
}
