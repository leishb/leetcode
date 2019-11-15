package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/8.
 */
public class _465_Optimal_Account_Balancing {


    /**
     * Accepted
     * @param transactions
     * @return
     */
    public int minTransfers(int[][] transactions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] trans : transactions){
            map.put(trans[0], map.getOrDefault(trans[0], 0)-trans[2]);
            map.put(trans[1], map.getOrDefault(trans[1], 0)+trans[2]);
        }
        int[] debits = new int[map.size()];
        int k = 0;
        for (int v : map.values()){
            debits[k++] = v;
        }
        return dfs(0, debits);
    }


    private int dfs(int start, int[] debits){
        while (start<debits.length && debits[start]==0){
            start++;
        }
        if (start==debits.length)return 0;
        int min = Integer.MAX_VALUE;
        for (int i=start+1;i<debits.length;i++){
            if (debits[start] * debits[i] < 0){
                int temp = debits[start];
                debits[i] +=temp;
                min= Math.min(min, 1+dfs(start+1, debits));
                debits[i] -= temp;
            }
        }
        return min;
    }
}
