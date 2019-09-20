package com.leishb.leetcode.dp;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/19.
 */
public class _1139_Largest_1_Bordered_Square {

    int[][] dirs = new int[][]{{1,0},{0,1},{-1,0},{0,-1}};

    public int largest1BorderedSquare(int[][] grid) {
        int max = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[i].length;j++){
                if (grid[i][j] == 1){
                    max = Math.max(max,1);
                    if (max < 4){
                        if (i+1<grid.length && j+1<grid[i].length && grid[i+1][j]==1 && grid[i][j+1]==1 && grid[i+1][j+1]==1) max = 4;
                    }
                }else if (!visited[i][j]){
                    List<int[]> list = new ArrayList<>();
                    bfs(grid, list, i, j, visited);
                    max = Math.max(max, checkSquare(list, grid));
                }
            }
        }
        return max;
    }


    private void bfs(int[][] grid , List<int[]> list, int i, int j, boolean[][] visited){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        list.add(new int[]{i,j});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            for (int[] dir : dirs){
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if (x>=0 && y>=0 && x<grid.length && y<grid[0].length && !visited[x][y] && grid[x][y]==0){
                    queue.offer(new int[]{x,y});
                    list.add(new int[]{x,y});
                    visited[x][y] = true;
                }
            }
        }
    }


    private int checkSquare(List<int[]> list, int[][] grid){
        int size = list.size();
        Collections.sort(list, (a1, a2)->a1[0]==a2[0]?a1[1]-a2[1]:a1[0]-a2[0]);
        int[] min = list.get(0);
        int[] max = list.get(list.size()-1);
        if (min[0]==0||min[1]==0 || max[0] ==grid.length-1 || max[1]==grid[0].length-1 ){
            return 0;
        }
        if (max[0]-min[0]!=max[1]-min[1])return 0;
        if ((max[0]-min[0]+1) * (max[0]-min[0]+1) == size){
            return (max[0]-min[0]+3) * (max[0]-min[0]+3);
        }
        return 0;
    }



    public int largest1BorderedSquare2(int[][] grid) {
        int[][] right = new int[grid.length][grid[0].length];
        int[][] down = new int[grid.length][grid[0].length];
        int[][] left = new int[grid.length][grid[0].length];
        int[][] up = new int[grid.length][grid[0].length];
        for (int i=grid.length-1;i>=0;i--){
            for (int j=grid[0].length-1;j>=0;j--){
                if (grid[i][j]==1){
                    right[i][j] = j<grid[0].length-1?right[i][j+1]+1:1;
                    down[i][j] = i<grid.length-1?down[i+1][j] +1:1;
                }
            }
        }
        int ans = 0;
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    left[i][j] = j>0?left[i][j-1] +1:1;
                    up[i][j] = i>0?up[i-1][j] +1:1;
                    int min = Math.min(left[i][j], up[i][j]);
                    int x = i-min+1;
                    int y= j-min+1;
                    while (x<=i && y<=j && (right[x][y]< j-y+1 || down[x][y] < i-x+1)){
                        x++;
                        y++;
                    }
                    if (right[x][y]>= j-y+1 && down[x][y]>= i-x+1){
                        ans = Math.max(ans, (i-x+1)*(j-y+1));
                    }
                }
            }
        }
        return ans;
    }

    public int largest1BorderedSquare3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] left = new int[m][n];
        int[][] top = new int[m][n];
        for (int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    left[i][j] = j > 0 ? left[i][j - 1] + 1 : 1;
                    top[i][j] = i > 0 ? top[i - 1][j] + 1 : 1;
                }
            }
        }
        for (int l= Math.min(m, n);l>0;l--){
            for (int i=0;i<m-l+1;i++){
                for (int j=0;j<n-l+1;j++){
                    if (left[i][j+l-1]>=l && left[i+l-1][j+l-1]>=l
                            && top[i+l-1][j]>=l && top[i+l-1][j+l-1]>=l){
                        return l*l;
                    }
                }
            }
        }
        return 0;
    }
    @Test
    public void test(){
//        Assert.assertTrue(largest1BorderedSquare2(new int[][]{{1,1,1},{1,0,1},{1,1,1}})==9);
        Assert.assertTrue(largest1BorderedSquare3(new int[][]
                        {{1,1,1,1,1,1,1,1}
                        ,{0,1,0,1,1,1,1,0}
                        ,{1,1,0,1,0,1,1,1}
                        ,{1,1,1,1,1,1,0,0}})==9);
    }
}
