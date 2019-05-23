package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/5/17.
 */
public class MinHeightTree {


    int minHeight =  Integer.MAX_VALUE;

    /**
     * TLE
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<n;i++){
            boolean[] visisted = new boolean[n];
            dfs(i, i, edges, map, visisted, 0);
            minHeight = Math.min(minHeight, map.get(i));
        }
        for (int k : map.keySet()){
            if (map.get(k) == minHeight){
                result.add(k);
            }
        }
        return result;
    }


    private void dfs(int k, int next,  int[][] edges, Map<Integer, Integer> map, boolean[] visited, int level){
        if (level > minHeight){
            map.put(k, level);
            return;
        }
        visited[next] = true;
        for (int[] arr : edges){
            if (arr[0] == next && !visited[arr[1]]){
                dfs(k, arr[1], edges, map, visited, level+1);
            }else if (arr[1] == next && !visited[arr[0]]){
                dfs(k, arr[0], edges, map, visited, level+1);
            }
        }
        if (!map.containsKey(k) || map.get(k) < level){
            map.put(k, level);
        }
    }


    /**
     * TLE
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees2(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, List<Integer>> em1 = new HashMap<>();
        Map<Integer, List<Integer>> em2 = new HashMap<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<n;i++){
            em1.put(i, new ArrayList<>());
            em2.put(i, new ArrayList<>());
        }
        for (int[] arr : edges){
            em1.getOrDefault(arr[0],new ArrayList<>()).add(arr[1]);
            em2.getOrDefault(arr[1],new ArrayList<>()).add(arr[0]);
        }
        for (int i=0;i<n;i++){
            boolean[] visisted = new boolean[n];
            dfs(i, i, em1, em2, map, visisted, 0);
            minHeight = Math.min(minHeight, map.get(i));
        }
        for (int k : map.keySet()){
            if (map.get(k) == minHeight){
                result.add(k);
            }
        }
        return result;
    }


    private void dfs(int k, int next,  Map<Integer,  List<Integer>> em1,Map<Integer,  List<Integer>> em2, Map<Integer, Integer> map, boolean[] visited, int level){
        if (level > minHeight){
            map.put(k, level);
            return;
        }
        visited[next] = true;
        for (Integer x : em1.get(next)){
            if (!visited[x]){
                dfs(k, x, em1, em2, map, visited, level+1);
            }
        }
        for (Integer x : em2.get(next)){
            if (!visited[x]){
                dfs(k, x, em1, em2, map, visited, level+1);
            }
        }
        if (!map.containsKey(k) || map.get(k) < level){
            map.put(k, level);
        }
    }


    /**
     * Accepted
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTrees3(int n, int[][] edges) {
        if (n==1){
            return Arrays.asList(0);
        }
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i=0;i<n;i++){
            adj.add(new HashSet<>());
        }
        for (int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i=0;i<adj.size();i++){
            if (adj.get(i).size()==1){
                leaves.add(i);
            }
        }
        while (n > 2){
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int k : leaves){
                int x = adj.get(k).iterator().next();
                adj.get(x).remove(k);
                if (adj.get(x).size()==1){
                    newLeaves.add(x);
                }
            }
            leaves = newLeaves;
        }
        return leaves;
    }




    @Test
    public void test(){
        System.out.println(findMinHeightTrees2(6, new int[][] {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
        System.out.println(findMinHeightTrees3(6, new int[][] {{0, 3}, {1, 3}, {2, 3}, {4, 3}, {5, 4}}));
    }
}
