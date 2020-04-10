package com.leishb.leetcode.bfs;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2020/1/21.
 */
public class _499_The_Maze_III {


    int[] dirs = new int[]{0, 1, 0, -1, 0};
    String[] strs = new String[]{"r", "d", "l", "u"};


    /**
     * Accepted
     * @param maze
     * @param ball
     * @param hole
     * @return
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        int[][] dist = new int[m][n];
        String[][] path = new String[m][n];
        for(int i=0;i<m;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            Arrays.fill(path[i], "z");
        }
        Queue<int[]> queue = new LinkedList();
        queue.offer(ball);
        dist[ball[0]][ball[1]] = 0;
        path[ball[0]][ball[1]] = "";
        int min = Integer.MAX_VALUE;
        String res = "impossible";
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int i=0;i<4 ;i++ ) {
                int count = 0;
                int x = cur[0];
                int y = cur[1];
                while(x>=0 && y>=0 && x<m && y<n &&maze[x][y]!=1){
                    x += dirs[i];
                    y += dirs[i+1];
                    count++;
                    if (x==hole[0] && y==hole[1]){
                        if (min > dist[cur[0]][cur[1]] + count
                                || (dist[cur[0]][cur[1]] + count == min && res.compareTo(path[cur[0]][cur[1]]+strs[i]) > 0)){
                            res= path[cur[0]][cur[1]]+strs[i];
                            min = dist[cur[0]][cur[1]] + count;
                        }
                    }
                }
                count--;
                x -= dirs[i];
                y -= dirs[i+1];
                if (dist[cur[0]][cur[1]] + count < dist[x][y]
                        || (dist[cur[0]][cur[1]] + count == dist[x][y] && path[x][y].compareTo(path[cur[0]][cur[1]]+strs[i]) > 0)) {
                    dist[x][y] = dist[cur[0]][cur[1]] + count;
                    path[x][y] = path[cur[0]][cur[1]] + strs[i];
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return res;
    }


    @Test
    public void test(){
        System.out.println(findShortestWay(new int[][]{{0,0,0,0,0},{1,1,0,0,1},{0,0,0,0,0},{0,1,0,0,1},{0,1,0,0,0}}, new int[]{4,3}, new int[]{0,1}));
        System.out.println(findShortestWay(new int[][]{{0,0,0},{0,1,0},{0,0,0}} ,new int[]{1,0},new int[]{1,2}));
    }
}
