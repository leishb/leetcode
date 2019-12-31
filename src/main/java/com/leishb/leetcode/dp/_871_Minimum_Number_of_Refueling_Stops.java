package com.leishb.leetcode.dp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/12/13.
 */
public class _871_Minimum_Number_of_Refueling_Stops {


    /**
     * TLE
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int min = dfs(target, startFuel, stations, 0, new HashMap<>());
        return min>stations.length?-1:min;
    }



    private int dfs(int target, int fuel, int[][] stations, int index, Map<String, Integer> memo){
        if(fuel>=target){
            return 0;
        }
        int prev = 0;
        if (index>0){
            prev = stations[index-1][0];
        }
        if (index==stations.length || fuel-(stations[index][0]-prev)<0){
            return stations.length+1;
        }
        int dist = stations[index][0]-prev;
        String key = target+","+fuel+","+index;
        if (memo.containsKey(key)) return memo.get(key);
        int refuel = dfs(target-dist, fuel+stations[index][1]-dist, stations, index+1, memo) + 1;
        int noRefuel = dfs(target-dist, fuel-dist, stations, index+1, memo);
        int min = Math.min(refuel, noRefuel);
        memo.put(key, min);
        return min;
    }


    /**
     * Accepted
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public int minRefuelStops2(int target, int startFuel, int[][] stations) {
        int n =stations.length;
        int[] dp = new int[n+1];
        dp[0] = startFuel;
        for (int i=0;i<n;i++){
            for (int j=i+1;j>=1;j--){
                if (dp[j-1] >= stations[i][0]){
                    dp[j] = Math.max(dp[j], dp[j-1] + stations[i][1]);
                }
            }
        }
        for (int i=0;i<=n;i++){
            if (dp[i]>=target){
                return i;
            }
        }
        return -1;
    }


    /**
     * Accepted
     * @param target
     * @param startFuel
     * @param stations
     * @return
     */
    public int minRefuelStops3(int target, int startFuel, int[][] stations) {
        Queue<Integer> pq = new PriorityQueue<>((a, b)->b-a);
        int stops = 0, i = 0, cur = startFuel;
        while (i<stations.length){
            if (cur>=target) return stops;
            if (cur>=stations[i][0]){
                pq.offer(stations[i][1]);
                i++;
            }else if (!pq.isEmpty()){
                cur += pq.poll();
                stops++;
            }else {
                return -1;
            }
        }
        while (!pq.isEmpty()){
            if (cur>=target) return stops;
            cur +=pq.poll();
            stops++;
        }
        return cur>=target?stops:-1;
    }


    @Test
    public void test(){
        minRefuelStops3(100, 10, new int[][]{{10,60},{20,30},{30,30},{60,40}});
    }
}
