package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by me on 2019/11/13.
 */
public class _1168_Optimize_Water_Distribution_in_a_Village {


    /**
     * Accepted
     * @param n
     * @param wells
     * @param pipes
     * @return
     */
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        int[] parents = new int[n+1];
        for (int i=0;i<n+1;i++){
            parents[i] = i;
        }
        int[][] np = new int[pipes.length+n][3];
        for (int i=0;i<pipes.length;i++){
            np[i] = new int[]{pipes[i][0]-1,pipes[i][1]-1,pipes[i][2]};
        }
        int j = 0;
        for (int i=pipes.length;i<np.length;i++){
            np[i] = new int[]{j, n, wells[j]};
            j++;
        }
        Arrays.sort(np, (n1, n2)->n1[2]-n2[2]);
        int cost = 0;
        for (int i=0;i<np.length;i++){
            int x = np[i][0];
            int y = np[i][1];
            int rx = find(x, parents);
            int ry = find(y, parents);
            if (rx==ry) continue;
            cost+=np[i][2];
            parents[rx] = ry;
        }
        return cost;
    }


    private int find(int x, int[] parents){
        if (x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }


    @Test
    public void test(){
        Assert.assertTrue(minCostToSupplyWater(5, new int[]{46012,72474,64965,751,33304}, new int[][]{{2,1,6719},{3,2,75312},{5,3,44918}})
        ==131704);
    }
}
