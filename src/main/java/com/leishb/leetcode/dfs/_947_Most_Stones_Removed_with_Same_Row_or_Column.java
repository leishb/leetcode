package com.leishb.leetcode.dfs;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by me on 2019/9/4.
 */
public class _947_Most_Stones_Removed_with_Same_Row_or_Column {


    public int removeStones(int[][] stones) {
        int N = stones.length;
        List<Integer>[] adj = new List[N];
        for (int i=0;i<N;i++){
            adj[i] = new ArrayList<>();
            for (int j=0;j<N;j++){
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]){
                    adj[i].add(j);
                }
            }
        }
        int ans = 0;
        boolean[] visited = new boolean[N];
        for (int i=0;i<N;i++){
            if (!visited[i]){
                ans += dfs(adj, visited, i);
            }
        }
        return ans;
    }


    private int dfs( List<Integer>[] adj, boolean[] visited, int index){
        visited[index] = true;
        int ans = 0;
        for (int k : adj[index]){
            if (!visited[k]){
                ans += dfs(adj, visited, k)+1;
            }
        }
        return ans;
    }

    public int removeStones2(int[][] stones) {
        DSU dsu = new DSU(20000);
        for (int[] stone : stones){
            dsu.union(stone[0], stone[1]+10000);
        }
        Set<Integer> seen = new HashSet<>();
        for (int[] stone : stones){
            seen.add(dsu.find(stone[0]));
        }
        return stones.length-seen.size();
    }



    class DSU{
        int[] parent;
        int[] rank ;
        DSU(int n){
            parent = new int[n];
            rank = new int[n];
            for (int i=0;i<n;i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x){
            while (x!=parent[x]){
                x = parent[x];
            }
            return x;
        }

        public void union(int x, int y){
            int rootX = find(x);
            int rootY = find(y);
            if (rootX!=rootY){
                if (rank[rootX] < rank[rootY]){
                    parent[rootX] = rootY;
                    rank[rootY] += rank[rootX];
                }else {
                    parent[rootY] = rootX;
                    rank[rootX] +=rank[rootY];
                }
            }
        }
    }

    @Test
    public void test(){
        Assert.assertTrue(removeStones2(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}})==5);
        Assert.assertTrue(removeStones2(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}})==3);
        Assert.assertTrue(removeStones2(new int[][]{{0,0}})==0);
        Assert.assertTrue(removeStones2(new int[][]{{0,1},{1,0},{1,1}})==2);
    }
}
