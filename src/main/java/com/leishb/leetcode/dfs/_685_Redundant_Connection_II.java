package com.leishb.leetcode.dfs;

import java.util.*;

/**
 * Created by me on 2019/10/29.
 */
public class _685_Redundant_Connection_II {


    /**
     * Accepted
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i=0;i<n;i++){
            graph.putIfAbsent(edges[i][0], new HashSet<>());
            graph.get(edges[i][0]).add(edges[i][1]);
        }
        List<Integer> rootList = new ArrayList<>();
        for (int i=1;i<=n;i++){
            Set<Integer> visited = new HashSet<>();
            dfs(graph, visited, i);
            if (visited.size()==n)rootList.add(i);
        }
        for (int i = edges.length-1;i>=0;i--){
            int[] edge = edges[i];
            graph.get(edge[0]).remove(edge[1]);
            for (int root : rootList){
                Set<Integer> visited = new HashSet<>();
                dfs(graph, visited, root);
                if (visited.size()==n)return edge;
            }
            graph.get(edge[0]).add(edge[1]);
        }
        return null;
    }


    private void dfs(Map<Integer, Set<Integer>> graph, Set<Integer> visited, int cur){
        visited.add(cur);
        for (int next : graph.getOrDefault(cur, new HashSet<>())){
            if (visited.contains(next)) continue;
            dfs(graph, visited, next);
        }
    }

    /**
     * Accepted
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection2(int[][] edges) {
        Map<Integer, Integer> incoming = new HashMap<>();
        int nodeWithTwoParents = -1;
        for (int[] edge : edges){
            incoming.put(edge[1], incoming.getOrDefault(edge[1], 0)+1);
            if (incoming.get(edge[1]) > 1){
                nodeWithTwoParents = edge[1];
            }
        }
        if (nodeWithTwoParents==-1){
            return findRedundantConnection(edges, -1);
        }
        for (int i=edges.length-1;i>=0;i--){
            if (edges[i][1]== nodeWithTwoParents){
                int[] res = findRedundantConnection(edges, i);
                if (res==null) return edges[i];
            }
        }
        return null;
    }


    private int[] findRedundantConnection(int[][] edges, int skip){
        int[] ids = new int[edges.length+1];
        for (int i=0;i<ids.length;i++){
            ids[i] = i;
        }
        for (int i = 0;i<edges.length;i++){
            if (i==skip) continue;
            int[] edge = edges[i];
            int rx = find(edge[0], ids);
            int ry = find(edge[1], ids);
            if (rx==ry) return edge;
            ids[rx] = ry;
        }
        return null;
    }

    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }
}
