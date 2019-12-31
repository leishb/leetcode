package com.leishb.leetcode.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/11/21.
 */
public class _1059_All_Paths_from_Source_Lead_to_Destination {


    /**
     * Accepted
     * @param n
     * @param edges
     * @param source
     * @param destination
     * @return
     */
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] adj = new List[n];
        for (int i=0;i<n;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
        }
        if (adj[destination].size()>0) return false;
        return dfs(adj, source, destination, new boolean[n]);
    }


    private boolean dfs(List<Integer>[] adj, int source, int dest, boolean[] visited){
        if (source==dest) return true;
        visited[source] = true;
        if (adj[source].size()==0) return false;
        for (int next : adj[source]){
            if (visited[next]) return false;
            if (!dfs(adj, next, dest, visited)){
                return false;
            }
        }
        visited[source] = false;
        return true;
    }
}
