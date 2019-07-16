package com.leishb.leetcode.bfs;

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
            if (!graph.containsKey(s)){
                graph.put(s, new ArrayList<>());
            }
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
                }else if (graph.get(flight[1])!=null){
                    for (int[] ff : graph.get(flight[1])){
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
}
