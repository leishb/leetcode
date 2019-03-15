package com.leishb.leetcode.graph;

import com.leishb.leetcode.tag.DFS;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by me on 2018/1/23.
 */
@DFS
public class NumIslands {


    @Test
    public void test(){
        char[][] grid = new char[][]{{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        char[][] grid2 = new char[][]{{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        char[][] grid3 = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        Assert.assertTrue(numIslands(grid)==1);
        Assert.assertTrue(numIslands(grid2)==1);
        Assert.assertTrue(numIslands(grid3)==3);
    }


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        if(grid.length==0){
            return 0;
        }
        int count = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i=0; i<grid.length; i++){
            for(int j=0; j<grid[i].length; j++){
                if (grid[i][j] == '1' && !visited[i][j]){
                    dfs(i, j, grid, visited);
                    count++;
                }
            }
        }
        return count;
    }


    private void dfs(int x, int y, char[][] grid, boolean[][] visited){
        if (x<0 || y < 0 || x>=grid.length || y >= grid[0].length){
            return;
        }
        if (grid[x][y] == '0' || visited[x][y]){
            return;
        }
        System.out.println(x + "," + y);
        visited[x][y] = true;
        dfs(x+1,y,grid,visited);
        dfs(x,y+1,grid,visited);
        dfs(x-1,y,grid,visited);
        dfs(x,y-1,grid,visited);
    }
}
