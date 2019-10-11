package com.leishb.leetcode.dfs;

import java.util.*;

/**
 * Created by me on 2019/10/11.
 */
public class _417_Pacific_Flow {

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> ans = new ArrayList<>();
        if (matrix.length==0) return ans;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] reachPacific = new boolean[m][n];
        boolean[][] reachAtlantic = new boolean[m][n];
        for(int i=0;i<m;i++){
            dfs(matrix, i, 0, reachPacific);
            dfs(matrix, i, n-1, reachAtlantic);
        }
        for(int i=0;i<n;i++){
            dfs(matrix, 0, i, reachPacific);
            dfs(matrix, m-1, i, reachAtlantic);
        }
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (reachAtlantic[i][j] && reachPacific[i][j]){
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    int[][] dirs = new int[][]{{-1, 0},{0, -1},{1, 0},{0, 1}};

    private void dfs(int[][] matrix,  int i, int j, boolean[][] visited){
        visited[i][j] = true;
        for (int[] dir : dirs){
            int x = i+dir[0];
            int y = j+dir[1];
            if (x<0 || y<0 || x>=matrix.length || y>=matrix[0].length || visited[x][y] || matrix[i][j] > matrix[x][y]){
                continue;
            }
            dfs(matrix, x, y, visited);
        }
    }
}
