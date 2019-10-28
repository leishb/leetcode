package com.leishb.leetcode.dfs.weight_path;

import java.util.*;

/**
 * Created by me on 2019/10/28.
 */
public class _1102_Path_With_Maximum_Minimum_Value {


    /**
     * TLE
     * @param A
     * @return
     */
    public int maximumMinimumPath(int[][] A) {
        int[][] dist = new int[A.length][A[0].length];
        for (int i=0;i<A.length;i++){
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = A[0][0];
        dfs(A, 0, 0, dist);
        return max;
    }


    private int max = Integer.MIN_VALUE;

    int[][] dirs = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    private void dfs(int[][] A, int i, int j, int[][] dist) {
        if (i == A.length - 1 && j == A[0].length - 1) {
            max = Math.max(dist[i][j], max);
            return;
        }
        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];
            if (x >= 0 && x < A.length && y >= 0 && y < A[0].length && Math.min(dist[i][j], A[x][y]) > dist[x][y]) {
                dist[x][y] = Math.min(dist[i][j], A[x][y]);
                dfs(A, x, y, dist);
            }
        }
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int maximumMinimumPath2(int[][] A) {
        int[][] dist = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = A[0][0];
        Queue<int[]> queue = new PriorityQueue<>((a, b)->b[2]-a[2]);
        queue.offer(new int[]{0, 0, A[0][0]});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if (cur[0]==A.length-1 && cur[1]==A[0].length-1){
                return cur[2];
            }
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if (x >= 0 && x < A.length && y >= 0 && y < A[0].length && dist[x][y]==-1) {
                    dist[x][y] = Math.min(A[x][y], cur[2]);
                    queue.offer(new int[]{x, y, dist[x][y]});
                }
            }
        }
        return 0;
    }

    /**
     * Accepted
     * @param A
     * @return
     */
    public int maximumMinimumPath3(int[][] A) {
        int[][] dist = new int[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            Arrays.fill(dist[i], -1);
        }
        dist[0][0] = A[0][0];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, A[0][0]});
        int ans = 0;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == A.length - 1 && cur[1] == A[0].length - 1) {
                ans = Math.max(ans, cur[2]);
            }
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                if (x >= 0 && x < A.length && y >= 0 && y < A[0].length && Math.min(A[x][y], cur[2]) > dist[x][y]) {
                    dist[x][y] = Math.min(A[x][y], cur[2]);
                    queue.offer(new int[]{x, y, dist[x][y]});
                }
            }
        }
        return ans;
    }


    /**
     * Accepted
     * @param A
     * @return
     */
    public int maximumMinimumPath4(int[][] A) {
        int m = A.length, n = A[0].length;
        List<int[]> list = new ArrayList<>();
        for (int i=0;i<m;i++){
            for (int j=0;j<n;j++){
                list.add(new int[]{i, j, A[i][j]});
            }
        }
        Collections.sort(list, (a, b)->b[2]-a[2]);
        boolean[][] visited = new boolean[m][n];
        int[] parents = new int[m*n];
        for (int i=0;i<parents.length;i++){
            parents[i] = i;
        }
        for (int[] arr : list){
            int i = arr[0], j= arr[1] , w = arr[2];
            visited[i][j] = true;
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];
                if (x >= 0 && x < A.length && y >= 0 && y < A[0].length && visited[x][y]) {
                    union(i*n+j, x*n+y, parents);
                }
            }
            if (find(0, parents)==find(m*n-1, parents)) return w;
        }
        return -1;
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }

    private void union(int x, int y, int[] parents){
        int rx = find(x, parents);
        int ry = find(y, parents);
        if (rx==ry) return;
        parents[rx] = ry;
    }
}
