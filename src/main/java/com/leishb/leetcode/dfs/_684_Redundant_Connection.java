package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/7/30.
 */
public class _684_Redundant_Connection {


    /**
     * Accepted
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges){
            graph.put(edge[0], graph.getOrDefault(edge[0], new ArrayList<>()));
            graph.get(edge[0]).add(edge[1]);
            graph.put(edge[1], graph.getOrDefault(edge[1], new ArrayList<>()));
            graph.get(edge[1]).add(edge[0]);
        }
        Stack<Integer> stack = new Stack<>();
        dfs(graph,-1, 1,  new boolean[edges.length+1], stack);
        Set<String> set = new HashSet<>();
        int k = stack.pop();
        int prev = k;
        while (stack.peek()!=k){
            int i = stack.pop();
            if (i>prev){
                set.add(prev+"-"+i);
            }else {
                set.add(i+"-"+prev);
            }
            prev = i;
        }
        if (k>prev){
            set.add(prev+"-"+k);
        }else {
            set.add(k+"-"+prev);
        }
        for (int i=edges.length-1;i>=0;i--){
            if (set.contains(edges[i][0]+"-"+edges[i][1])){
                return edges[i];
            }
        }
        return new int[]{0,0};
    }



    private boolean dfs(Map<Integer, List<Integer>> graph, int prev,  int k,  boolean[] onStack, Stack<Integer> stack){
        if (onStack[k]){
            stack.push(k);
            return true;
        }
        stack.push(k);
        onStack[k] = true;
        for (Integer i : graph.getOrDefault(k, new ArrayList<>())){
            if (i!=prev){
                if (dfs(graph, k,  i, onStack, stack)){
                    return true;
                }
            }
        }
        onStack[k] = false;
        stack.pop();
        return false;
    }


    /**
     * Accepted
     * @param edges
     * @return
     */
    public int[] findRedundantConnection2(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.put(edge[0], graph.getOrDefault(edge[0], new ArrayList<>()));
            graph.get(edge[0]).add(edge[1]);
            graph.put(edge[1], graph.getOrDefault(edge[1], new ArrayList<>()));
            graph.get(edge[1]).add(edge[0]);
        }
        for (int i=edges.length-1;i>=0;i--){
            graph.get(edges[i][0]).remove((Object)edges[i][1]);
            if (dfs(graph, edges[i][0], edges[i][1], new boolean[edges.length+1])){
                return edges[i];
            }
            graph.get(edges[i][0]).add(edges[i][1]);
        }
        return new int[2];
    }



    private boolean dfs(Map<Integer, List<Integer>> graph, int source , int target, boolean[] visited){
        if (source==target){
            return true;
        }
        visited[source]=true;
        for (Integer nei : graph.getOrDefault(source, new ArrayList<>())){
            if (visited[nei]){
                continue;
            }
            if (dfs(graph, nei, target, visited)){
                return true;
            }
        }
        return false;
    }


    public int[] findRedundantConnection3(int[][] edges) {
        int[] set = new int[edges.length+1];
        for (int i=0;i<set.length;i++){
            set[i] = i;
        }
        for (int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            int root_u = find(u, set);
            int root_v = find(v, set);
            if (root_u==root_v){
                return edge;
            }
            union(root_u, root_v, set);
        }
        return new int[2];
    }

    private int find(int u, int[] parent){
        if (parent[u]!=u){
            return find(parent[u], parent);
        }
        return parent[u];
    }

    private void union(int root_u, int root_v, int[] parent){
        parent[root_u] = root_v;
    }


    @Test
    public void test(){
        findRedundantConnection3(new int[][]{{1,2},{1,3},{2,3}});
        findRedundantConnection3(new int[][]{{2,7},{7,8},{3,6},{2,5},{6,8},{4,8},{2,8},{1,8},{7,10},{3,9}});
    }
}
