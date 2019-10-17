package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by me on 2019/10/14.
 */
public class _305_Number_of_Islands_II {


    int[][] dirs = new int[][]{{-1, 0},{0, -1},{1, 0},{0, 1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int[][] grid = new int[m][n];
        List<Integer> ans = new ArrayList<>();
        int[] parents = new int[m*n];
        int count = 0;
        for (int[] p : positions){
            if (grid[p[0]][p[1]]==1){
                ans.add(count);
                continue;
            }
            grid[p[0]][p[1]] = 1;
            parents[p[0] * n + p[1]] = p[0] * n + p[1];
            Set<Integer> set = new HashSet<>();
            for (int i=0;i<4;i++){
                int x = p[0] + dirs[i][0];
                int y = p[0] + dirs[i][1];
                if (x>=0 && y>=0 && x<m && y<n && grid[x][y] == 1){
                    set.add(find(parents, x*n+y));
                    union(x*n+y, p[0] * n + p[1], parents);
                }
            }
            count = count+1-set.size();
            ans.add(count);
        }
        return ans;
    }

    private int find(int[] parents, int x){
        if (x == parents[x]) return x;
        parents[x] = find(parents, parents[x]);
        return parents[x];
    }


    private void union(int x, int y, int[] parents){
        int px = find(parents, x);
        int py = find(parents, y);
        if (px==py) return;
        parents[py] = px;
    }


    @Test
    public void test(){
        numIslands2(3,3, new int[][]{{0,0},{0,1},{1,2},{1,2}});
    }
}
