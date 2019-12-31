package com.leishb.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/11/20.
 */
public class _1162_As_Far_from_Land_as_Possible {



    int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    /**
     * Accepted
     * @param grid
     * @return
     */
    public int maxDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                dist[i][j] = -1;
                if (grid[i][j]==1){
                    queue.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }
        if (queue.isEmpty() || queue.size()==m*n) return -1;
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] cur = queue.poll();
                for (int[] dir : dirs){
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x>=0 && y>=0 && x<m && y<n && dist[x][y]==-1){
                        queue.offer(new int[]{x, y});
                        dist[x][y] = step+1;
                    }
                }
            }
            step++;
        }
        int ans = -1;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                ans = Math.max(dist[i][j], ans);
            }
        }
        return ans;
    }
}
