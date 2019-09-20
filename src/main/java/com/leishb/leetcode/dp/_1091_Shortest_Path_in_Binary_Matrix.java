package com.leishb.leetcode.dp;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/9/16.
 */
public class _1091_Shortest_Path_in_Binary_Matrix {

    public int shortestPathBinaryMatrix(int[][] grid) {
        if (grid[0][0]==1) return -1;
        int[][] dirs = new int[][]{{0,1},{-1,0},{1,0},{-1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        boolean[][] visited = new boolean[grid.length][grid.length];
        visited[0][0] = true;
        int ans = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] p = queue.poll();
                if (p[0]== grid.length-1 && p[1]==grid.length-1){
                    return ans;
                }
                for (int[] dir : dirs){
                    int r = dir[0] + p[0];
                    int c = dir[1] + p[1];
                    if (r>=0 && c>=0 && r<grid.length && c< grid.length && grid[r][c]==0 && !visited[r][c]){
                        queue.offer(new int[]{r,c});
                        visited[r][c] = true;
                    }
                }
            }
            ans++;
        }
        return -1;
    }


    @Test
    public void test(){

    }
}
