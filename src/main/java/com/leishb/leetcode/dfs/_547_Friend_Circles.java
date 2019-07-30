package com.leishb.leetcode.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/7/30.
 */
public class _547_Friend_Circles {

    public int findCircleNum(int[][] M) {
        int count = 0;
        boolean[] visited = new boolean[M.length];
        for (int i=0;i<M.length;i++){
            if (!visited[i]){
                dfs(M, i, visited);
                count++;
            }
        }
        return count;
    }


    private void dfs(int[][] M, int k, boolean[] visited){
        visited[k] = true;
        for (int i=0;i<M[k].length;i++){
            if (M[k][i]==1 && !visited[i]){
                dfs(M, i, visited);
            }
        }
    }


    public int findCircleNum2(int[][] M) {
        int[] parent = new int[M.length];
        for (int i=0;i<parent.length;i++){
            parent[i] = i;
        }
        for (int i=0;i<M.length;i++){
            for (int j=0;j<=i;j++){
                if (M[i][j]==1){
                    int rootX = find(i, parent);
                    int rootY = find(j, parent);
                    if (rootX!=rootY){
                        union(rootX, rootY, parent);
                    }
                }
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<M.length;i++){
            set.add(find(i, parent));
        }
        return set.size();
    }


    private int find(int x, int[] parent){
        if (parent[x]!=x){
            return find(parent[x], parent);
        }
        return parent[x];
    }

    private void union(int rootX, int rootY, int[] parent){
        parent[rootX] = rootY;
    }
}
