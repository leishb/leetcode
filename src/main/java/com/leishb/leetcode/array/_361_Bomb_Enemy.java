package com.leishb.leetcode.array;

import org.junit.Test;

/**
 * Created by me on 2019/10/15.
 */
public class _361_Bomb_Enemy {

    public int maxKilledEnemies(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] left = new int[m][n];
        int[][] right = new int[m][n];
        int[][] up = new int[m][n];
        int[][] down = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]=='W') continue;
                left[i][j] = j>0?left[i][j-1]:0;
                up[i][j] = i>0?up[i-1][j]:0;
                if (grid[i][j]=='E'){
                    left[i][j] += 1;
                    up[i][j] += 1;
                }
            }
        }
        int max = 0;
        for (int i=m-1;i>=0;i--){
            for (int j=n-1;j>=0;j--){
                if (grid[i][j]=='W') continue;
                right[i][j] = j<n-1?right[i][j+1]:0;
                down[i][j] = i<m-1?down[i+1][j]:0;
                if (grid[i][j]=='E'){
                    right[i][j] += 1;
                    down[i][j] += 1;
                }
                if (grid[i][j]=='0'){
                    max = Math.max(max, left[i][j]+right[i][j] +
                            up[i][j] + down[i][j]);
                }
            }
        }
        return max;
    }


    @Test
    public void test(){
        maxKilledEnemies(new char[][]{
                {'0','E','0','0'},
                {'E','0','W','E'},
                {'0','E','0','0'}});
    }
}
