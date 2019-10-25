package com.leishb.leetcode.bfs;

import java.util.Arrays;

/**
 * Created by me on 2019/10/22.
 */
public class _1135_Connecting_Cities_With_Minimum_Cost {



    public int minimumCost(int N, int[][] connections) {
        Arrays.sort(connections, (c1, c2)->c1[2]-c2[2]);
        int[] parents = new int[N+1];
        for (int i=0;i<=N;i++){
            parents[i] = i;
        }
        int cost = 0;
        for (int[] conn : connections){
            int rx = find(conn[0], parents);
            int ry = find(conn[1], parents);
            if (rx==ry) continue;
            cost += conn[2];
            parents[rx] = ry;
        }
        int p = find(1, parents);
        for (int i=2;i<=N;i++){
            if (find(i, parents)!=p) return -1;
        }
        return cost;
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }
}
