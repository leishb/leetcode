package com.leishb.leetcode.dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * Created by me on 2019/9/10.
 */
public class _1020_Number_of_Enclaves {


    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public int numEnclaves(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int total = 0;
        Set<Integer> canFinish = new HashSet<>();
        Set<Integer> canNotFinish = new HashSet<>();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (A[i][j]==0){
                    continue;
                }
                total++;
                if (!canFinish.contains(i*n+j) && !canNotFinish.contains(i*n+j)){
                    Set<Integer> cur = new HashSet<>();
                    if (dfs(A, i, j, canFinish, cur)){
                        canFinish.addAll(cur);
                    }else {
                        canNotFinish.addAll(cur);
                    }
                }
            }
        }
        return total-canFinish.size();
    }


    private boolean dfs(int[][] A, int i, int j, Set<Integer> canFinish, Set<Integer> cur){
        if (i<0 || j<0 || i>=A.length || j>=A[0].length || canFinish.contains(i*A[0].length+j)){
            return true;
        }
        if (A[i][j]==0){
            return false;
        }
        int k = i*A[0].length + j;
        cur.add(k);
        for (int[] dir : dirs){
            int x = i+dir[0];
            int y = j+dir[1];
            if (cur.contains(x*A[0].length+y)){
                continue;
            }
            if (dfs(A, x, y, canFinish, cur)){
                return true;
            }
        }
        return false;
    }


    public int numEnclaves2(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int total = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1){
                    total+=1;
                }
                if ((i==0||j==0||i==m-1||j==n-1) && A[i][j] == 1){
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }
        int reached = 0;
        while (!queue.isEmpty()){
            int[] pair = queue.poll();
            reached+=1;
            for (int[] dir : dirs){
                int x = pair[0]+dir[0];
                int y = pair[1]+dir[1];
                if (x>=0 && x<m && y>=0 && y<n && !visited[x][y] && A[x][y]==1){
                    visited[x][y] = true;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        return total-reached;
    }
}
