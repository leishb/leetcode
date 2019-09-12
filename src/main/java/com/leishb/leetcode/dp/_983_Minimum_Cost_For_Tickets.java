package com.leishb.leetcode.dp;

import org.junit.Test;

import java.util.Arrays;
import java.util.TreeMap;

/**
 * Created by me on 2019/9/9.
 */
public class _983_Minimum_Cost_For_Tickets {

    public int mincostTickets(int[] days, int[] costs) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i=0;i<days.length;i++){
            map.put(days[i], i);
        }
        map.put(400,days.length);
        int[] memo = new int[days.length];
        Arrays.fill(memo, -1);
        return dfs(days, costs, map, 0, memo);
    }


    private int dfs(int[] days, int[] costs, TreeMap<Integer, Integer> map, Integer index, int[] memo){
        if (index>=days.length){
            return 0;
        }
        if (memo[index]!=-1){
            return memo[index];
        }
        for (int i=index;i<days.length;i++){
            int c1 = dfs(days, costs, map, map.higherEntry(days[i]).getValue(), memo) + costs[0];
            int c2 = dfs(days, costs, map, map.higherEntry(days[i]+6).getValue(), memo) + costs[1];
            int c3 = dfs(days, costs, map, map.higherEntry(days[i]+29).getValue(), memo) + costs[2];
            int min = Math.min(Math.min(c1, c2), c3);
            memo[index] = min;
            return min;
        }
        return 0;
    }


    public int mincostTickets2(int[] days, int[] costs) {
        TreeMap<Integer, Integer> map = new TreeMap();
        for(int i = 0;i<days.length;i++){
            map.put(days[i], i);
        }
        int[] dp = new int[days.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = Math.min(costs[0], Math.min(costs[1],costs[2]));
        for(int i=1;i<days.length;i++){
            Integer k = map.floorKey(days[i]-1);
            dp[i] = Math.min(dp[i], k==null?costs[0]:dp[map.get(k)]+costs[0]);
            k = map.floorKey(days[i]-7);
            dp[i] = Math.min(dp[i], k==null?costs[1]:dp[map.get(k)]+costs[1]);
            k = map.floorKey(days[i]-30);
            dp[i] = Math.min(dp[i], k==null?costs[2]:dp[map.get(k)]+costs[2]);
        }
        return dp[days.length-1];
    }

    public int mincostTickets3(int[] days, int[] costs) {
        boolean[] dayInclude = new boolean[366];
        for (int day : days){
            dayInclude[day] = true;
        }
        int[] minCost = new int[366];
        for (int i=1;i<=365;i++){
            if (!dayInclude[i]){
                minCost[i] =minCost[i-1];
                continue;
            }
            int min = minCost[i-1] + costs[0];
            min = Math.min(min, minCost[Math.max(0, i-7)]+costs[1]);
            min = Math.min(min, minCost[Math.max(0, i-30)]+costs[2]);
            minCost[i] = min;
        }
        return minCost[365];
    }
    @Test
    public void test(){

    }
}
