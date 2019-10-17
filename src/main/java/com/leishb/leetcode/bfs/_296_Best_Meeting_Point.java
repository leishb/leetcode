package com.leishb.leetcode.bfs;

import java.util.*;

/**
 * Created by me on 2019/10/15.
 */
public class _296_Best_Meeting_Point {

    public int minTotalDistance(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==0) continue;
                bfs(dist, i, j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (dist[i][j]==0) continue;
                min = Math.min(min, dist[i][j]);
            }
        }
        return min;
    }

    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    private void bfs(int[][] dist, int i, int j){
        int m = dist.length;
        int n = dist[0].length;
        boolean[][] visited = new boolean[dist.length][dist[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        int step = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] cur = queue.poll();
                dist[cur[0]][cur[1]] += step;
                for (int[] dir : dirs){
                    int x = dir[0]+cur[0];
                    int y = dir[1]+cur[1];
                    if (x<0 || y<0 || x>=m || y>=n || visited[x][y]){
                        continue;
                    }
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
            step++;
        }
    }


    public int minTotalDistance2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==1){
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        Collections.sort(cols);
        int row = rows.get(rows.size()/2);
        int col = cols.get(cols.size()/2);
        return dist(rows, row) + dist(cols, col);
    }


    private int dist(List<Integer> rows, int orig){
        int d = 0;
        for (int r : rows){
            d += Math.abs(r-orig);
        }
        return d;
    }
}
