package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by me on 2019/8/22.
 */
public class _886_Possible_Bipartition {


    /**
     * Accepted
     * @param N
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int N, int[][] dislikes) {
        List<Integer>[] adj = new List[N+1];
        for (int i=0;i<=N;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] d : dislikes){
            adj[d[0]].add(d[1]);
            adj[d[1]].add(d[0]);
        }
        int[] group = new int[N+1];
        for (int i=1;i<=N;i++){
            if (group[i]==0){
                group[i]=1;
                if (!dfs(adj, i, group)){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean dfs(List<Integer>[] adj, int cur, int[] group){
        for (int next : adj[cur]){
            if (group[next]!=0 && group[cur] == group[next]){
                return false;
            }
            if (group[next]==0){
                group[next] = group[cur]==1?2:1;
                if (!dfs(adj, next, group)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean possibleBipartition2(int N, int[][] dislikes) {
        List<Integer>[] adj = new List[N+1];
        for (int i=0;i<=N;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] d : dislikes){
            adj[d[0]].add(d[1]);
            adj[d[1]].add(d[0]);
        }
        int[] group = new int[N+1];
        for (int i=1;i<=N;i++){
            if (group[i]==0){
                group[i] = 1;
                if (!bfs(adj, i, group)){
                    return false;
                }
            }
        }
        return true;
    }


    private boolean bfs(List<Integer>[] adj, int cur, int[] group){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(cur);
        while (!queue.isEmpty()){
            int k = queue.poll();
            for (int next : adj[k]){
                if (group[next]!=0 && group[next]==group[k]){
                    return false;
                }
                if (group[next]==0){
                    group[next] = group[k]==1?2:1;
                    queue.offer(next);
                }
            }
        }
        return true;
    }

    @Test
    public void test(){
        Assert.assertTrue(possibleBipartition(4, new int[][] {{1,2},{1,3},{2,4}}));
        Assert.assertFalse(possibleBipartition(3, new int[][] {{1,2},{1,3},{2,3}}));
        Assert.assertFalse(possibleBipartition(5, new int[][]  {{1,2},{2,3},{3,4},{4,5},{1,5}}));
    }
}
