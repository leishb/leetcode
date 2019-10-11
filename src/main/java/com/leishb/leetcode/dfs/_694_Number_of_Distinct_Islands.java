package com.leishb.leetcode.dfs;

import java.util.*;

/**
 * Created by me on 2019/10/8.
 */
public class _694_Number_of_Distinct_Islands {

    public int numDistinctIslands(int[][] grid) {
        List<List<int[]>> ans = new ArrayList<>();
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (!visited[i][j] && grid[i][j] == 1){
                    List<int[]> list = new ArrayList<>();
                    dfs(grid, i, j, list, visited);
                    Collections.sort(list, (a1, a2)->a1[0]!=a2[0]?a1[0]-a2[0]:a1[1]-a2[1]);
                    ans.add(list);
                }
            }
        }
        int[] parents = new int[ans.size()];
        for (int i=0;i<parents.length;i++){
            parents[i] = i;
        }
        for (int i=0;i<ans.size();i++){
            for (int j=i+1;j<ans.size();j++){
                if (isSame(ans.get(i), ans.get(j))){
                    union(i, j, parents);
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<parents.length;i++){
            set.add(find(i, parents));
        }
        return set.size();
    }


    private void union(int x, int y, int[] parents){
        int rootx = find(x, parents);
        int rooty = find(y, parents);
        if (rootx==rooty)return;
        parents[rootx] = rooty;
    }

    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }


    private boolean isSame(List<int[]> list1, List<int[]> list2){
        if (list1.size()!=list2.size())return false;
        int diffX = list1.get(0)[0]-list2.get(0)[0];
        int diffY = list1.get(0)[1]-list2.get(0)[1];
        for (int i=1;i<list1.size();i++){
            if (list1.get(i)[0]-list2.get(i)[0] != diffX || list1.get(i)[1]-list2.get(i)[1] != diffY){
                return false;
            }
        }
        return true;
    }


    int[][] dirs = {{0, 1},{1, 0},{-1, 0},{0, -1}};

    private void dfs(int[][] grid, int i, int j, List<int[]> list, boolean[][] visited){
        if (i<0 || j<0 || i>=grid.length || j>=grid[0].length || visited[i][j] || grid[i][j] == 0){
            return;
        }
        list.add(new int[]{i, j});
        visited[i][j] = true;
        for (int[] dir : dirs){
            dfs(grid, i+dir[0], j+dir[1], list, visited);
        }
    }


    public int numDistinctIslands2(int[][] grid) {
        Set<String> set = new HashSet<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j] == 1){
                    StringBuffer sb = new StringBuffer();
                    dfs(grid, i, j, sb, "o");
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }


    private void dfs(int[][] grid, int i, int j, StringBuffer sb, String dir){
        if (i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j] == 0){
            return;
        }
        grid[i][j] = 0;
        sb.append(dir);
        dfs(grid, i-1,j ,sb, "u");
        dfs(grid, i+1,j ,sb, "d");
        dfs(grid, i,j-1 ,sb, "l");
        dfs(grid, i,j+1 ,sb, "r");
        sb.append("_");
    }
}
