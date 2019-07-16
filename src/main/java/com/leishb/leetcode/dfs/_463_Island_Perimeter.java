package com.leishb.leetcode.dfs;

/**
 * Created by me on 2019/7/12.
 */
public class _463_Island_Perimeter {


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int p = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == 0){
                    continue;
                }
                if (i-1<0 || grid[i-1][j] == 0){
                    p++;
                }
                if (j-1<0 || grid[i][j-1] == 0){
                    p++;
                }
                if (i+1>=grid.length || grid[i+1][j] == 0){
                    p++;
                }
                if (j+1>=grid[0].length || grid[i][j+1] == 0){
                    p++;
                }
            }
        }
        return p;
    }
}
