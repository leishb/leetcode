package com.leishb.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by me on 2019/9/3.
 */
public class _934_Shortest_Bridge {


    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
    /**
     * Accepted
     * @param A
     * @return
     */
    public int shortestBridge(int[][] A) {
        int n = A.length;
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (set.size()>0){
                    break;
                }
                dfs(A, i, j, set);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int k : set){
            int i = k/A.length;
            int j = k%A.length;
            int steps = bfs(A, i, j, set);
            if (steps!=-1){
                min = Math.min(min ,steps);
            }
        }
        return min;
    }


    private int bfs(int[][] A, int i, int j, Set<Integer> set){
        Queue<int[]> queue = new LinkedList<>();
        int steps = 0;
        queue.offer(new int[]{i,j});
        boolean[][] visited = new boolean[A.length][A.length];
        visited[i][j] = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int p=0;p<size;p++){
                int[] pair = queue.poll();
                for (int[] dir : dirs){
                    int x = pair[0]+dir[0];
                    int y = pair[1]+dir[1];
                    if (x>=0 && y>=0 && x<A.length && y<A.length ){
                        if (A[x][y]==1 && !set.contains(x*A.length+y)){
                            return steps;
                        }
                        if (!visited[x][y] && !set.contains(x*A.length+y)){
                            visited[x][y] = true;
                            queue.offer(new int[]{x, y});
                        }
                    }
                }
            }
            steps++;
        }
        return -1;
    }


    private void dfs(int[][] A, int i, int j, Set<Integer> set){
        if (i<0||j<0||i>=A.length||j>=A.length || A[i][j]!=1){
            return;
        }
        if (set.contains(i*A.length+j)){
            return;
        }
        set.add(i*A.length+j);
        dfs(A, i+1, j, set);
        dfs(A, i, j+1, set);
        dfs(A, i-1, j, set);
        dfs(A, i, j-1, set);
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int shortestBridge2(int[][] A) {
        int n = A.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){
                if (A[i][j]==1){
                    dfs(A, i, j, queue, visited);
                    break;
                }
            }
            if (queue.size()>0){
                break;
            }
        }
        int steps = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int[] cur = queue.poll();
                for (int[] dir : dirs){
                    int x = cur[0]+dir[0];
                    int y = cur[1]+dir[1];
                    if (x>=0 && y>=0 && x<A.length && y<A.length && !visited[x][y]){
                        if (A[x][y]==1){
                            return steps;
                        }
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
            steps++;
        }
        return steps;
    }

    private void dfs(int[][] A, int i, int j, Queue<int[]> queue, boolean[][] visited){
        if (i<0||j<0||i>=A.length||j>=A.length || A[i][j]!=1 || visited[i][j]){
            return;
        }
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        for (int[] dir : dirs){
            dfs(A, i+dir[0], j+dir[1], queue, visited);
        }
    }
    @Test
    public void test(){
        Assert.assertTrue(shortestBridge2(new int[][]{{1,1,1,1,1}
                ,{1,0,0,0,1}
                ,{1,0,1,0,1}
                ,{1,0,0,0,1}
                ,{1,1,1,1,1}})==1);
        Assert.assertTrue(shortestBridge2(new int[][]{{0,1},{1,0}})==1);
        Assert.assertTrue(shortestBridge2(new int[][]{{0,1,0}
                ,{0,0,0}
                ,{0,0,1}})==2);
        Assert.assertTrue(shortestBridge2(new int[][]{
                {1,1,0,0,0}
                ,{1,0,0,0,0}
                ,{1,0,0,0,0}
                ,{0,0,0,1,1}
                ,{0,0,0,1,1}})==3);
    }
}
