package com.leishb.leetcode.dp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2020/1/6.
 */
public class _174_Dungeon_Game {

    int m, n;

    public int calculateMinimumHP(int[][] dungeon) {
        m = dungeon.length;
        n = dungeon[0].length;
        int low = 1;
        int high = 1_000_000;
        Map<String, Boolean> memo = new HashMap<>();
        while (low<=high){
            int m = (high-low)/2+low;
            if (dfs(dungeon, m, 0, 0, memo)){
                high = m-1;
            }else {
                low = m+1;
            }
            System.out.println(low + "-->" +high);
        }
        return low;
    }



    private boolean dfs(int[][] dungeon, int health, int i, int j, Map<String, Boolean> memo){
        String key = health + "," + i + "," + j;
        if (memo.containsKey(key)) return memo.get(key);
        if (i>=m || j>=n || health+dungeon[i][j]<=0 ) return false;
        if (i==m-1 && j == n-1) return true;
        boolean result = dfs(dungeon, health+dungeon[i][j], i+1, j, memo) || dfs(dungeon, health+dungeon[i][j], i, j+1, memo);
        memo.put(key, result);
        return result;
    }


    /**
     * Accepted
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP2(int[][] dungeon) {
        m = dungeon.length;
        n = dungeon[0].length;
        int[][] dp = new int[m][n];
        dp[m-1][n-1] = dungeon[m-1][n-1]>=0?1:(1-dungeon[m-1][n-1]);
        for (int i=m-1;i>=0;i--){
            for (int j=n-1;j>=0;j--){
                if (i==m-1 && j==n-1) continue;
                if (i==m-1){
                    dp[i][j] = dp[i][j+1];
                }else if (j == n - 1) {
                    dp[i][j] = dp[i+1][j];
                }else {
                    dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]);
                }
                if (dungeon[i][j] < 0){
                    dp[i][j] -= dungeon[i][j];
                }else if (dungeon[i][j] < dp[i][j]){
                    dp[i][j] -= dungeon[i][j];
                }else {
                    dp[i][j] = 1;
                }
            }
        }
        return dp[0][0];
    }

    @Test
    public void test(){
    }
}
