package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/10/17.
 */
public class _490_The_Maze {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        return dfs(maze, start[0], start[1], destination, new boolean[maze.length][maze[0].length]);
    }

    int[][] dir = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};


    private boolean dfs(int[][] maze, int i, int j,  int[] destination, boolean[][] visited){
        if (visited[i][j]){
            return false;
        }
        if (i==destination[0] && j==destination[1]){
            return true;
        }
        visited[i][j] = true;
        for (int[] d : dir){
            int x = i+d[0];
            int y = j+d[1];
            while (x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y]==0){
                x += d[0];
                y += d[1];
            }
            if (dfs(maze, x-d[0] , y-d[1], destination, visited)){
                return true;
            }
        }
        return false;
    }

    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if (cur[0]==destination[0] && cur[1]==destination[1]){
                return true;
            }
            for (int[] d : dir){
                int x = cur[0]+d[0];
                int y = cur[1]+d[1];
                while (x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y]==0){
                    x += d[0];
                    y += d[1];
                }
                x -= d[0];
                y-= d[1];
                if (!visited[x][y]){
                    queue.offer(new int[]{x, y});
                    visited[x][y] = true;
                }
            }
        }
        return false;
    }



    @Test
    public void test(){
        int[][] maze = {{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        int[] start = {0, 4};
        int[] dest = {4,4};
        Assert.assertTrue(hasPath2(maze, start, dest));
    }
}
