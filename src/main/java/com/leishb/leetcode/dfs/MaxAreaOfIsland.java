package com.leishb.leetcode.dfs;

import com.leishb.leetcode.tag.DFS;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/4/29.
 */
@DFS
public class MaxAreaOfIsland {

    int maxArea = 0;

    /**
     * Accepted
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid.length==0){
            return 0;
        }
        boolean[][] visisted = new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (visisted[i][j] || grid[i][j] == 0){
                    continue;
                }
                List<Integer> list = new ArrayList<>();
                list.add(0);
                dfs(grid, visisted, i, j, list);
                maxArea = Math.max(maxArea, list.get(0));
            }
        }
        return maxArea;
    }


    private void dfs(int[][] grid , boolean[][] visisted, int i, int j, List<Integer> area){
        if (i<0 || j< 0 || i==grid.length || j==grid[0].length || grid[i][j] == 0 || visisted[i][j]){
            return;
        }
        visisted[i][j] = true;
        area.set(0, area.get(0)+1);
        dfs(grid, visisted, i+1,j,area);
        dfs(grid, visisted, i,j+1,area);
        dfs(grid, visisted, i-1,j,area);
        dfs(grid, visisted, i,j-1,area);
    }
}
