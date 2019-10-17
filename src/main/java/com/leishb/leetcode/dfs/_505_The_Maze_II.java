package com.leishb.leetcode.dfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/10/17.
 */
public class _505_The_Maze_II {

    int[][] dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};

    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        queue.offer(start);
        int dist = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs){
                int count = 0;
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                while (x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y]==0){
                    x += dir[0];
                    y += dir[1];
                    count++;
                }
                x -= dir[0];
                y -= dir[1];
                if (distance[cur[0]][cur[1]] + count < distance[x][y]){
                    distance[x][y] = distance[cur[0]][cur[1]] + count;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return distance[destination[0]][destination[1]]==Integer.MAX_VALUE?-1:distance[destination[0]][destination[1]];
    }


    public int shortestDistance2(int[][] maze, int[] start, int[] destination) {
        int[][] distance = new int[maze.length][maze[0].length];
        for (int[] row : distance){
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        distance[start[0]][start[1]] = 0;
        dfs(maze, start, distance);
        return distance[destination[0]][destination[1]]==Integer.MAX_VALUE?-1:distance[destination[0]][destination[1]];
    }


    private void dfs(int[][] maze, int[] start, int[][] distance){
        for (int[] dir : dirs) {
            int count = 0;
            int x = start[0] + dir[0];
            int y = start[1] + dir[1];
            while (x>=0 && y>=0 && x<maze.length && y<maze[0].length && maze[x][y]==0){
                x += dir[0];
                y += dir[1];
                count++;
            }
            if (distance[start[0]][start[1]] + count < distance[x-dir[0]][y-dir[1]]){
                distance[x-dir[0]][y-dir[1]] = distance[start[0]][start[1]] + count;
                dfs(maze, new int[]{x-dir[0], y-dir[1]}, distance);
            }
        }
    }
}
