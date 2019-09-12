package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2019/9/5.
 */
public class _959_Regions_Cut_By_Slashes {


    int[][] dirs = new int[][]{{0,-1,2},{-1,0,2},{0,1,-2},{1,0,-2}};

    int[] leftSlash = new int[]{1, 0, 3, 2};
    int[] rightSlash = new int[]{3, 2, 1, 0};

    /**
     * Accepted
     * @param grid
     * @return
     */
    public int regionsBySlashes(String[] grid) {
        int count = 0;
        int N = grid.length;
        boolean[][][] visited = new boolean[N][N][4];
        for (int i=0;i<N;i++){
            for (int j=0;j<N;j++){
                for (int k=0;k<4;k++){
                    if (!visited[i][j][k]){
                        dfs(grid, i, j, k, visited);
                        count++;
                    }
                }
            }
        }
        return count;
    }


    private void dfs(String[] grid, int i, int j, int k, boolean[][][] visited){
        if (i<0 || j<0 || i>=grid.length || j>=grid.length || k<0 || k>=4 || visited[i][j][k]){
            return;
        }
        visited[i][j][k] = true;
        char c = grid[i].charAt(j);
        if (c=='/'){
            dfs(grid, i, j, leftSlash[k], visited);
        }else if (c=='\\'){
            dfs(grid, i, j, rightSlash[k], visited);
        }else {
            dfs(grid, i, j, leftSlash[k], visited);
            dfs(grid, i, j, rightSlash[k], visited);
        }
        dfs(grid, i+dirs[k][0], j+dirs[k][1], k+dirs[k][2], visited);
    }


    @Test
    public void test(){
        Assert.assertTrue(regionsBySlashes(new String[]{" /", "/ "})==2);
        Assert.assertTrue(regionsBySlashes(new String[]{  "\\/", "/\\"}) == 4);
        Assert.assertTrue(regionsBySlashes(new String[]{  "//", "/ "}) == 3);
    }
}
