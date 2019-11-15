package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/11/15.
 */
public class _1245_Tree_Diameter {


    /**
     * Accepted
     * @param edges
     * @return
     */
    public int treeDiameter(int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i=0;i<edges.length;i++){
            map.putIfAbsent(edges[i][0], new HashSet<>());
            map.putIfAbsent(edges[i][1], new HashSet<>());
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        Map<Integer, Integer> leaves = new HashMap<>();
        for (int k : map.keySet()){
            if (map.get(k).size()==1){
                leaves.put(k, map.get(k).iterator().next());
            }
        }
        int level = 0;
        while (map.size() > 2){
            Map<Integer, Integer> newLeaves = new HashMap<>();
            for (int k : leaves.keySet()){
                map.get(leaves.get(k)).remove(k);
                map.remove(k);
            }
            level++;
            for (int k : leaves.keySet()){
                if (map.get(leaves.get(k)).size()==1){
                    newLeaves.put(leaves.get(k), map.get(leaves.get(k)).iterator().next());
                }
            }
            leaves = newLeaves;
        }
        return level*2 + map.size()-1;
    }


    /**
     * Accepted
     * @param edges
     * @return
     */
    public int treeDiameter2(int[][] edges) {
        int n = edges.length+1;
        Set<Integer>[] adj = new Set[n];
        for (int i=0;i<n;i++){
            adj[i] = new HashSet<>();
        }
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0;i<n;i++){
            if (adj[i].size()==1){
                leaves.add(i);
            }
        }
        int level = 0;
        while (n > 2){
            n-=leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int x : leaves){
                int y = adj[x].iterator().next();
                adj[y].remove(x);
                if (adj[y].size()==1){
                    newLeaves.add(y);
                }
            }
            level++;
            leaves = newLeaves;
        }
        return level*2 + n-1;
    }




    public int treeDiameter3(int[][] edges) {
        int n = edges.length+1;
        List<Integer>[] adj = new List[n];
        for (int i=0;i<n;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        dfs(0, -1, 0, adj);
        dfs(best[1], -1, 0, adj);
        return best[0];
    }


    int[] best = new int[]{-1, -1};

    private void dfs(int start, int parent, int len, List<Integer>[] adj){
        if (len > best[0]){
            best = new int[]{len, start};
        }
        for (int next : adj[start]){
            if (next!=parent){
                dfs(next, start, len+1, adj);
            }
        }
    }


    /**
     * Accepted
     * @param edges
     * @return
     */
    public int treeDiameter4(int[][] edges) {
        int n = edges.length + 1;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] best = bfs(0, adj, n);
        return bfs(best[0], adj, n)[1];
    }


    private int[] bfs(int start, List<Integer>[] adj, int n){
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        dist[start] = 0;
        while (!queue.isEmpty()){
            int p = queue.poll();
            for (int next : adj[p]){
                if (dist[next]==-1){
                    queue.offer(next);
                    dist[next] = dist[p] + 1;
                }
            }
        }
        int maxIndex = 0;
        for (int i=1;i<dist.length;i++){
            if (dist[i] > dist[maxIndex]){
                maxIndex = i;
            }
        }
        return new int[]{maxIndex, dist[maxIndex]};
    }


    @Test
    public void test(){
        treeDiameter3(new int[][]{{0,1},{0,2}});
    }
}
