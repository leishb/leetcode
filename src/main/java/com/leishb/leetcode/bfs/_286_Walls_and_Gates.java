package com.leishb.leetcode.bfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/10/8.
 */
public class _286_Walls_and_Gates {

    public void wallsAndGates(int[][] rooms) {
        if (rooms.length==0)return;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rooms.length][rooms[0].length];
        for (int i=0;i<rooms.length;i++){
            for (int j=0;j<rooms[i].length;j++){
                if (rooms[i][j]==0){
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int step = 0;
        int[][] dirs = new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int[] cur = queue.poll();
                rooms[cur[0]][cur[1]] = step;
                for (int[] dir : dirs){
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    if (x >=0 && x<rooms.length && y>=0 && y<rooms[0].length && !visited[x][y] && rooms[x][y]==Integer.MAX_VALUE){
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            step++;
        }
    }
}
