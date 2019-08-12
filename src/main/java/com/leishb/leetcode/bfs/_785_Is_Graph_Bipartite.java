package com.leishb.leetcode.bfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by me on 2019/8/12.
 */
public class _785_Is_Graph_Bipartite {


    /**
     * Accepted
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int[] sets = new int[graph.length];
        Arrays.fill(sets, -1);
        for (int i=0;i<graph.length;i++){
            if (sets[i]==-1){
                if (!isBipartite(graph, sets, i)){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean isBipartite(int[][] graph, int[] sets, int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        sets[start] = 0;
        while (!queue.isEmpty()){
            int prev = queue.poll();
            for (int i =0;i<graph[prev].length;i++){
                if (sets[graph[prev][i]]==-1){
                    sets[graph[prev][i]] = sets[prev]^1;
                    queue.offer(graph[prev][i]);
                }else if (sets[graph[prev][i]]==sets[prev]){
                    return false;
                }
            }
        }
        return true;
    }


    public boolean isBipartite2(int[][] graph) {
        int[] sets = new int[graph.length];
        Arrays.fill(sets, -1);
        for (int i=0;i<graph.length;i++){
            if (sets[i]==-1){
                sets[i] = 0;
                if (!dfs(graph, sets, i)){
                    return false;
                }
            }
        }
        return true;
    }



    private boolean dfs(int[][] graph, int[] sets, int start){
        for (int i=0;i<graph[start].length;i++){
            if (sets[graph[start][i]] == -1){
                sets[graph[start][i]] = sets[start]^1;
                if (!dfs(graph, sets, graph[start][i])){
                    return false;
                }
            }else if (sets[graph[start][i]] == sets[start]){
                return false;
            }
        }
        return true;
    }


    @Test
    public void test(){
        Assert.assertFalse(isBipartite(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}}));
        Assert.assertFalse(isBipartite(new int[][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}}));
        Assert.assertFalse(isBipartite2(new int[][]{{1,2,3},{0,2},{0,1,3},{0,2}}));
        Assert.assertFalse(isBipartite2(new int[][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}}));
    }
}
