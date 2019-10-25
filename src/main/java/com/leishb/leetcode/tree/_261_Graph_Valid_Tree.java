package com.leishb.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/10/21.
 */
public class _261_Graph_Valid_Tree {

    public boolean validTree(int n, int[][] edges) {
        int[] parents = new int[n];
        for (int i=0;i<n;i++){
            parents[i] = i;
        }
        for (int[] edge : edges){
            int rx = find(edge[0], parents);
            int ry = find(edge[1], parents);
            if (rx==ry){
                return false;
            }
            parents[rx] = ry;
        }
        int p = find(0, parents);
        for (int i=0;i<n;i++){
            if (find(i, parents)!=p) return false;
        }
        return true;
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }


    public boolean validTree2(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges){
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        if (!dfs(graph, 0, -1, visited)){
            return false;
        }
        for (int i=0;i<n;i++){
            if (!visited[i]){
                return false;
            }
        }
        return true;
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, int cur, int prev, boolean[] visited){
        visited[cur] = true;
        for (int next : graph.getOrDefault(cur, new ArrayList<>())){
            if (next==prev) continue;
            if (visited[next]){
                return false;
            }
            if (!dfs(graph, next, cur, visited)){
                return false;
            }
        }
        return true;
    }
}
