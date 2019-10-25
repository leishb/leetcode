package com.leishb.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/10/23.
 */
public class _323_Number_of_Connected_Components_in_an_Undirected_Graph {

    public int countComponents(int n, int[][] edges) {
        int count = 0;
        List<Integer>[] adj = new List[n];
        for (int i=0;i<n;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        for (int i=0;i<n;i++){
            if (!visited[i]){
                dfs(adj, i, visited);
                count++;
            }
        }
        return count;
    }

    private void dfs(List<Integer>[] adj, int cur, boolean[] visited){
        visited[cur] = true;
        for (int next : adj[cur]){
            if (!visited[next]){
                dfs(adj, next, visited);
            }
        }
    }

    public int countComponents2(int n, int[][] edges) {
        int count = n;
        int[] parents = new int[n];
        for (int i=0;i<n;i++){
            parents[i] = i;
        }
        for (int[] edge: edges){
            int rx = find(edge[0], parents);
            int ry = find(edge[1], parents);
            if (rx==ry) continue;
            count--;
            parents[rx] = ry;
        }
        return count;
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }
}
