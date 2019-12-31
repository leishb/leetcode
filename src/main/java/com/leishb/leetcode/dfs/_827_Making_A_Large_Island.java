package com.leishb.leetcode.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/10/25.
 */
public class _827_Making_A_Large_Island {


    /**
     * Accepted
     * @param grid
     * @return
     */
    public int largestIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        UF uf = new UF(m*n);
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                if (grid[i][j]==0) continue;
                if (i+1<m && grid[i+1][j]==1){
                    uf.union(i * n + j, (i+1) * n + j);
                }
                if (j+1<n && grid[i][j+1] == 1){
                    uf.union(i * n + j, i * n + j+1);
                }
            }
        }
        int max = 0;
        int[] dirs = new int[]{0, 1, 0, -1, 0};
        for (int i=0;i<m;i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    max = Math.max(uf.size[uf.find(i*n + j)], max);
                    continue;
                }
                Set<Integer> set = new HashSet<>();
                for (int d=0;d<4;d++){
                    int x = i + dirs[d];
                    int y = j + dirs[d+1];
                    if (x>=0 && x<m && y>=0 && y<n && grid[x][y]==1){
                        set.add(uf.find(x * n + y));
                    }
                }
                int count = 1;
                for (int p : set){
                    count += uf.size[p];
                }
                max = Math.max(max, count);
            }
        }
        return max;
    }


    class UF{

        int[] parents;
        int[] size;

        UF(int N){
            this.parents = new int[N];
            this.size = new int[N];
            for (int i=0;i<N;i++){
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x){
            if (x==parents[x]) return x;
            return parents[x] = find(parents[x]);
        }


        public void union(int x, int y){
            int rx = find(x);
            int ry = find(y);
            if (rx!=ry){
                parents[rx] = ry;
                size[ry] += size[rx];
            }
        }
    }
}
