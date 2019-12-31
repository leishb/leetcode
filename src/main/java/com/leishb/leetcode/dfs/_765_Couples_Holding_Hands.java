package com.leishb.leetcode.dfs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/27.
 */
public class _765_Couples_Holding_Hands {


    /**
     * Accepted
     * @param row
     * @return
     */
    public int minSwapsCouples(int[] row) {
        int[] parents = new int[row.length/2];
        for (int i=0;i<parents.length;i++){
            parents[i] = i;
        }
        for (int i=0;i<row.length;i+=2){
            int rx = find(row[i]/2, parents);
            int ry = find(row[i+1]/2, parents);
            if (rx!=ry) parents[rx] = ry;
        }
        Map<Integer, Integer> count = new HashMap<>();
        for (int i=0;i<parents.length;i++){
            int p = find(i, parents);
            count.put(p, count.getOrDefault(p, 0)+1);
        }
        int ans = 0;
        for (int k : count.keySet()){
            ans += count.get(k)-1;
        }
        return ans;
    }


    private int find(int x, int[] parents){
        if(x==parents[x]) return x;
        parents[x] = find(parents[x], parents);
        return parents[x];
    }
}
