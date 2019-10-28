package com.leishb.leetcode.dfs.weight_path;

import java.util.*;

/**
 * Created by me on 2019/7/16.
 */
public class _787_Cheapest_Flights_Within_K_Stops {


    /**
     * Accepted
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0;i<flights.length;i++){
            int s = flights[i][0];
            graph.putIfAbsent(s, new ArrayList<>());
            graph.get(s).add(flights[i]);
            if (s==src){
                queue.offer(new int[]{flights[i][0],flights[i][1],flights[i][2], 0});
            }
        }
        int stops = 0;
        int minPrice = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            if (stops>K){
                break;
            }
            int size = queue.size();
            for (int i=0;i<size;i++){
                int[] flight = queue.poll();
                int price = flight[3];
                if (flight[1]==dst){
                    minPrice = Math.min(minPrice, price+flight[2]);
                }else {
                    for (int[] ff : graph.getOrDefault(flight[1], new ArrayList<>())){
                        if (price+flight[2]<minPrice){
                            queue.offer(new int[]{ff[0],ff[1],ff[2], flight[2]+price});
                        }
                    }
                }
            }
            stops++;
        }
        return minPrice==Integer.MAX_VALUE?-1:minPrice;
    }



    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        Arrays.fill(prices, Integer.MAX_VALUE);
        prices[src] = 0;
        Map<Integer, List<int[]>> graph = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();
        for (int i=0;i<flights.length;i++){
            int s = flights[i][0];
            graph.putIfAbsent(s, new ArrayList<>());
            graph.get(s).add(flights[i]);
        }
        queue.offer(new int[]{src, 0});
        int stops = 0;
        int ans = Integer.MAX_VALUE;
        while (!queue.isEmpty()){
            int size = queue.size();
            if (stops-1>K)break;
            while (size-->0){
                int[] cur = queue.poll();
                if (cur[0]==dst){
                    ans = Math.min(ans, cur[1]);
                }
                for (int[] next : graph.getOrDefault(cur[0], new ArrayList<>())){
                    if (next[2]+cur[1] < prices[next[1]]){
                        prices[next[1]] =next[2]+cur[1];
                        queue.offer(new int[]{next[1], next[2]+cur[1]});
                    }
                }
            }
            stops++;
        }
        return ans == Integer.MAX_VALUE?-1:ans;
    }




    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        int[][] prices = new int[n+1][n];
        for (int i=0;i<=n;i++) {
            Arrays.fill(prices[i], Integer.MAX_VALUE);
        }
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i=0;i<flights.length;i++){
            int s = flights[i][0];
            graph.putIfAbsent(s, new ArrayList<>());
            graph.get(s).add(flights[i]);
        }
        Queue<int[]> queue =  new PriorityQueue<>((a, b)->a[2]-b[2]);
        queue.offer(new int[]{src, 0, 0});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            if (cur[1]-1>K){
                continue;
            }
            if (cur[0] == dst){
                return cur[2];
            }
            for (int[] next : graph.getOrDefault(cur[0], new ArrayList<>())){
                if (cur[2]+next[2] < prices[cur[1]][next[1]]){
                    prices[cur[1]][next[1]] = cur[2] + next[2];
                    queue.offer(new int[]{next[1], cur[1]+1, cur[2] + next[2]});
                }
            }
        }
        return -1;
    }

    public int findCheapestPrice4(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int i=0;i<flights.length;i++){
            graph.putIfAbsent(flights[i][0], new HashMap<>());
            graph.get(flights[i][0]).put(flights[i][1], flights[i][2]);
        }
        Queue<int[]> queue =  new PriorityQueue<>((a, b)->a[1]-b[1]);
        queue.offer(new int[]{src, 0, 0});
        while (!queue.isEmpty()){
            int[] cur = queue.poll();
            int start = cur[0];
            int price = cur[1];
            int stops = cur[2];
            if (start==dst){
                return price;
            }
            if (stops> K){
                continue;
            }
            for (int next : graph.getOrDefault(start, new HashMap<>()).keySet()){
                queue.offer(new int[]{next, price+graph.get(start).get(next), stops+1});
            }
        }
        return -1;
    }
}
