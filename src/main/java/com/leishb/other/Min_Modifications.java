package com.leishb.other;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/discuss/interview-question/476340/Google-or-Onsite-or-Min-Modifications
 * Created by me on 2020/1/16.
 */
public class Min_Modifications {


    int[][] dirs = new int[][]{{0, 1},{1, 0},{-1, 0},{0, -1}};
    char[] cs = new char[]{'R', 'D', 'U', 'L'};

    public int minModification(char[][] grid){
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int i=0;i<m;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE/2);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0,0});
        dist[0][0] = 0;
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int i=0;i<4;i++){
                int x = cur[0] + dirs[i][0];
                int y = cur[1] + dirs[i][1];
                if (x>=0 && x<m && y>=0 && y<n){
                    int modify = dist[cur[0]][cur[1]] + (grid[cur[0]][cur[1]]==cs[i]?0:1);
                    if (dist[x][y] > modify){
                        dist[x][y] = modify;
                        queue.offer(new int[]{x, y});
                    }
                }
            }
        }
        return dist[m-1][n-1];
    }

    @Test
    public void test(){
        char[][] arr = {{'R','R','D'},{'L','L','L'},{'U','U','R'}};
        char[][] arr2 = {{'D','L','L'},{'L','R','D'},{'U','U','R'}};
        System.out.println(minModification(arr));
        System.out.println(minModification(arr2));
    }
}
