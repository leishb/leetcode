package com.leishb.leetcode.bfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/10/31.
 */
public class _1197_Minimum_Knight_Moves {

    int[][] dirs = new int[][]{{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{-2,1},{2,-1},{-2,-1}};


    public int minKnightMoves(int x, int y) {
        if (Math.abs(x)==1 && Math.abs(y)==1) return 2;
        Queue<int[]> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();
        int step = 0;
        queue.offer(new int[]{0,0});
        set.add(0+","+0);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] cur = queue.poll();
                if (cur[0]==Math.abs(x) && cur[1]==Math.abs(y)){
                    return step;
                }
                for (int[] dir : dirs){
                    int i  = cur[0] + dir[0];
                    int j = cur[1] + dir[1];
                    if (i < 0 || j <0) continue;
                    String key = i+ "," + j;
                    if (set.contains(key)) continue;
                    set.add(key);
                    queue.offer(new int[]{i, j});
                }
            }
            step++;
        }
        return 0;
    }


    public int minKnightMoves2(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> queue = new LinkedList();
        queue.offer(new int[]{0, 0});
        Set<String> set = new HashSet();
        set.add(0 + "," + 0);
        int steps = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-->0){
                int[] cur = queue.poll();
                if (cur[0]==x && cur[1]==y) {
                    return steps;
                }
                for (int[] dir : dirs) {
                    int nx = cur[0] + dir[0];
                    int ny = cur[1] + dir[1];
                    if(nx < -2 || ny < -2 || nx > x+2
                            || ny > y+2 || set.contains(nx+","+ny)){
                        continue;
                    }
                    set.add(nx+","+ny);
                    queue.offer(new int[]{nx, ny});
                }
            }
            steps++;
        }
        return -1;
    }

    @Test
    public void test(){
        minKnightMoves(2,1);
        minKnightMoves(300,0);
    }
}
