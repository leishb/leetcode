package com.leishb.leetcode.dfs;

/**
 * Created by me on 2019/12/23.
 */
public class _803_Bricks_Falling_When_Hit {


    /**
     * TLE
     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int m = grid.length, n = grid[0].length;
        int[] ans = new int[hits.length];
        int total = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i=0;i<grid[0].length;i++){
            total += dfs(grid, 0, i, visited);
        }
        for (int i=0;i<hits.length;i++){
            grid[hits[i][0]][hits[i][1]] = 0;
            int count = 0;
            visited = new boolean[m][n];
            for (int j=0;j<grid[0].length;j++){
                count += dfs(grid, 0, j, visited);
            }
            ans[i] = total>count?total-count-1:0;
            total = count;
        }
        return ans;
    }

    private int dfs(int[][] grid, int x, int y, boolean[][] visited){
        if (x<0 || y<0 || x>=grid.length || y>=grid[0].length ||visited[x][y] || grid[x][y]==0){
            return 0;
        }
        int ans = 1;
        visited[x][y] = true;
        ans += dfs(grid, x+1, y, visited);
        ans += dfs(grid, x, y+1, visited);
        ans += dfs(grid, x-1, y, visited);
        ans += dfs(grid, x, y-1, visited);
        return ans;
    }




    int[] dirs = new int[]{0, 1, 0, -1, 0};

    int rows ;
    int cols;
    int[][] grid;


    /**
     * Accepted
     * @param grid
     * @param hits
     * @return
     */
    public int[] hitBricks2(int[][] grid, int[][] hits) {
        rows = grid.length;
        cols = grid[0].length;
        this.grid = grid;
        for (int[] hit : hits){
            if (grid[hit[0]][hit[1]]==1)grid[hit[0]][hit[1]] = 2;
        }
        DisjointSet ds = new DisjointSet(rows * cols +1);
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                if (grid[i][j]==1)unionAround(i, j, ds);
            }
        }
        int total = ds.size[ds.find(0)];
        int[] ans = new int[hits.length];
        for (int i=hits.length-1;i>=0;i--){
            if (grid[hits[i][0]][hits[i][1]]==2){
                grid[hits[i][0]][hits[i][1]] = 1;
                unionAround(hits[i][0], hits[i][1], ds);
                int connectedBricks = ds.size[ds.find(0)];
                ans[i] = Math.max(connectedBricks-total-1, 0);
                total = connectedBricks;
            }
        }
        return ans;
    }


    private void unionAround(int x, int y, DisjointSet ds){
        for (int i=0;i<4;i++){
            int nx = x + dirs[i];
            int ny = y + dirs[i+1];
            if (nx>=0 && ny>=0 && nx<rows && ny<cols && grid[nx][ny]==1){
                ds.union(x * cols + y +1, nx * cols + ny + 1);
            }
        }
        if (x==0) ds.union(x * cols + y +1, 0);
    }

    class DisjointSet{
        int[] parents;
        int[] size;

        DisjointSet(int N){
            parents = new int[N];
            for (int i=0;i<N;i++){
                parents[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x){
            if (x==parents[x]) return x;
            parents[x] = find(parents[x]);
            return parents[x];
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
