package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/10/25.
 */
public class _980_Unique_Paths_III {


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int uniquePathsIII(int[][] grid) {
        int totalZeros = 0;
        int[] start = new int[]{0, 0};
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == 0){
                    totalZeros++;
                }
                if (grid[i][j] == 1){
                    start = new int[]{i, j};
                }
            }
        }
        int count = dfs(grid, start[0], start[1], new boolean[grid.length][grid[0].length], 0, totalZeros);
        if (reached) return count;
        return 0;
    }

    int[][] dirs = new int[][]{{-1, 0},{0, -1},{1, 0},{0, 1}};
    boolean reached = false;

    private int dfs(int[][] grid, int i, int j, boolean[][] visited, int zeors, int total){
        int count = 0;
        for (int[] dir : dirs){
            int x = i + dir[0];
            int y = j + dir[1];
            if (valid(x, y , grid) && zeors==total && grid[x][y] == 2){
                reached = true;
                return 1;
            }
            if (valid(x, y , grid) && !visited[x][y] && grid[x][y] == 0){
                visited[x][y] = true;
                count += dfs(grid, x, y, visited, zeors+1, total);
                visited[x][y] = false;
            }
        }
        return count;
    }



    private boolean valid(int x, int y, int[][] grid){
        return x>=0 && y>=0 && x< grid.length && y < grid[0].length;
    }


    @Test
    public void test(){
        Assert.assertTrue(uniquePathsIII(new int[][]{{1,0,0,0},{0,0,0,0},{0,0,2,-1}})==2);
    }
}
