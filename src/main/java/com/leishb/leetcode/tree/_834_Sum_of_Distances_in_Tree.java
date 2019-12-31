package com.leishb.leetcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/11/21.
 */
public class _834_Sum_of_Distances_in_Tree {


    /**
     * Accepted
     * @param N
     * @param edges
     * @return
     */
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        List<Integer>[] adj = new List[N];
        for (int i=0;i<N;i++){
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        int[] counts = new int[N];
        buildCounts(adj, 0 ,-1, counts);
        int[] ans = new int[N];
        ans[0] = dfs(adj, 0, -1, counts);
        dfs(adj, 0, -1, ans, N, counts);
        return ans;
    }


    private void dfs(List<Integer>[] adj, int cur, int p, int[] dist, int N, int[] counts){
        for (int next : adj[cur]){
            if (next==p) continue;
            dist[next] = dist[cur] + N - 2 * (counts[next]-1) -2;
            dfs(adj, next, cur, dist, N, counts);
        }
    }

    private int dfs(List<Integer>[] adj, int cur, int p, int[] counts){
        int d = 0;
        for (int next : adj[cur]){
            if (next==p) continue;
            d += dfs(adj, next, cur, counts) + counts[next];
        }
        return d;
    }

    private int buildCounts(List<Integer>[] adj, int cur, int p, int[] counts){
        int x = 1;
        for (int next : adj[cur]){
            if (p==next) continue;
            x += buildCounts(adj, next, cur, counts) ;
        }
        counts[cur] = x;
        return x;
    }


    @Test
    public void test(){
        sumOfDistancesInTree(6, new int[][]{{0,1},{0,2},{2,3},{2,4},{2,5}});
        sumOfDistancesInTree(4, new int[][]{{1,2},{3,2},{3,0}});
    }
}
