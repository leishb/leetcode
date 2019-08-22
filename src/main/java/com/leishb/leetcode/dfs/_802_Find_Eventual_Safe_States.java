package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/8/15.
 */
public class _802_Find_Eventual_Safe_States {


    /**
     * Accepted
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Set<Integer> onStack = new HashSet<>();
        for (int i=0;i<graph.length;i++){
            if (onStack.contains(i)){
                continue;
            }
            if (!onStack.contains(i) && visited.contains(i)){
                ans.add(i);
                continue;
            }
            if (dfs(graph, i, visited, onStack)){
                ans.add(i);
            }
        }
        return ans;
    }


    private boolean dfs(int[][] graph, int start, Set<Integer> visited, Set<Integer> onStack){
        visited.add(start);
        onStack.add(start);
        for (int next : graph[start]){
            if (onStack.contains(next)){
                return false;
            }
            if (visited.contains(next)){
                continue;
            }
            if (!dfs(graph, next, visited, onStack)){
                return false;
            }
        }
        onStack.remove(start);
        return true;
    }


    /**
     * Accepted
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes2(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int[] color = new int[graph.length];
        for (int i=0;i<graph.length;i++){
            if (color[i]==1){
                continue;
            }
            if (color[i]==2){
                ans.add(i);
                continue;
            }
            if (dfs(graph, i, color)){
                ans.add(i);
            }
        }
        return ans;
    }


    private boolean dfs(int[][] graph, int start, int[] color){
        color[start] = 1;
        for (int next : graph[start]){
            if (color[next]==1){
                return false;
            }
            if (color[next]==2){
                continue;
            }
            if (!dfs(graph, next, color)){
                return false;
            }
        }
        color[start]=2;
        return true;
    }

    public List<Integer> eventualSafeNodes3(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        List<Set<Integer>> adj = new ArrayList<>();
        List<Set<Integer>> rAdj = new ArrayList<>();
        for (int i=0;i<graph.length;i++){
            adj.add(new HashSet<>());
            rAdj.add(new HashSet<>());
        }
        for (int i=0;i<graph.length;i++){
            for (int j : graph[i]){
                adj.get(i).add(j);
                rAdj.get(j).add(i);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<adj.size();i++){
            if (adj.get(i).isEmpty()){
                queue.offer(i);
            }
        }
        boolean[] safe = new boolean[graph.length];
        while (!queue.isEmpty()){
            int i = queue.poll();
            safe[i] = true;
            for (int j : rAdj.get(i)){
                adj.get(j).remove(i);
                if (adj.get(j).isEmpty()){
                    queue.offer(j);
                }
            }
        }
        for (int i=0;i<graph.length;i++){
            if (safe[i]) ans.add(i);
        }
        return ans;
    }

    @Test
    public void test(){
        System.out.println(eventualSafeNodes2(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
        System.out.println(eventualSafeNodes3(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
    }
}
