package com.leishb.leetcode.array;

/**
 * Created by me on 2019/11/14.
 */
public class _1219_Path_with_Maximum_Gold {


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int getMaximumGold(int[][] grid) {
        int max = 0;
        int m = grid.length, n = grid[0].length;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j] != 0){
                    max = Math.max(max, backtracking(grid, i, j));
                }
            }
        }
        return max;
    }

    private int backtracking(int[][] grid, int x, int y){
        int m = grid.length, n = grid[0].length;
        if (x<0 || y<0 || x>=m || y>=n || grid[x][y]==0){
            return 0;
        }
        int max = 0;
        int gold = grid[x][y];
        grid[x][y] = 0;
        max = Math.max(max, gold + backtracking(grid, x+1, y));
        max = Math.max(max, gold + backtracking(grid, x-1, y));
        max = Math.max(max, gold + backtracking(grid, x, y+1));
        max = Math.max(max, gold + backtracking(grid, x, y-1));
        grid[x][y] = gold;
        return max;
    }

    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    private int dfs(int[][] grid, int i, int j){
        int m = grid.length, n = grid[0].length;
        int gold = grid[i][j];
        int max = gold;
        grid[i][j] = 0;
        for (int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if (x>=0 && y>=0 && x<m && y<n && grid[x][y]!=0){
                max = Math.max(max, gold + dfs(grid, x, y));
            }
        }
        grid[i][j] = gold;
        return max;
    }


}
