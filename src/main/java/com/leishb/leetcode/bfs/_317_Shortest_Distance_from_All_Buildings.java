package com.leishb.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/9/25.
 */
public class _317_Shortest_Distance_from_All_Buildings {

    public int shortestDistance(int[][] grid) {
        int[][] dist = new int[grid.length][grid[0].length];
        int[][] count = new int[grid.length][grid[0].length];
        int buildings = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    buildings++;
                    bfs(grid, dist, count, i, j);
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (count[i][j]==buildings && grid[i][j]==0){
                    ans = Math.min(ans, dist[i][j]);
                }
            }
        }
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    private void bfs(int[][] grid, int[][] dist, int[][] count, int i, int j){
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        queue.offer(new int[]{i,j});
        visited[i][j] = true;
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] cur = queue.poll();
                dist[cur[0]][cur[1]] += step;
                count[cur[0]][cur[1]] +=1;
                for (int[] dir : dirs){
                    int x = cur[0]+dir[0];
                    int y = cur[1]+dir[1];
                    if (x>=0 && x<grid.length && y>=0 && y<grid[0].length && grid[x][y]==0 && !visited[x][y]){
                        queue.offer(new int[]{x,y});
                        visited[x][y] = true;
                    }
                }
            }
            step++;
        }
    }


    @Test
    public void test(){
        Assert.assertTrue(shortestDistance(new int[][]{{1,0,2,0,1},{0,0,0,0,0},{0,0,1,0,0}})==7);
    }
}
