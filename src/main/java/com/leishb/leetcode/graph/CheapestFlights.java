package com.leishb.leetcode.graph;

import java.util.*;

/**
 * Created by me on 2019/4/29.
 */
public class CheapestFlights {


    int minPrice = Integer.MAX_VALUE;


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
        for (int[] flight : flights){
            if (flight[0] == src){
                dfs(n, flights, flight, dst, K, 0, flight[2]);
            }
        }
        return minPrice==Integer.MAX_VALUE?-1:minPrice;
    }


    private void dfs(int n, int[][] flights, int[] src, int dst, int K, int depth, int price){
        if (depth > K){
            return;
        }
        if (price>minPrice){
            return;
        }
        if (src[1] == dst){
            minPrice = Math.min(minPrice, price);
            return;
        }
        for (int[] flight : flights){
            if (flight[0] == src[1]){
                dfs(n, flights, flight, dst, K, depth+1, price+flight[2]);
            }
        }
    }


    /**
     * Accepted
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
        for (int[] flight : flights){
            if (flight[0] == src){
                bfs(n, flights, flight, dst, K);
            }
        }
        return minPrice==Integer.MAX_VALUE?-1:minPrice;
    }

    private void bfs(int n, int[][] flights, int[] src, int dst, int K) {
        int depth = 0;
        Queue<int[]> queue = new LinkedList<>();
        Queue<Integer> prices = new LinkedList<>();
        queue.offer(src);
        prices.offer(0);
        while (!queue.isEmpty()){
            if (depth>K){
                break;
            }
            int size = queue.size();
            for (int i=0;i<size;i++){
                int[] flight = queue.poll();
                int price = prices.poll();
                if (flight[1] == dst){
                    minPrice = Math.min(minPrice, price+flight[2]);
                }else if (minPrice > price+flight[2]){
                    for (int[] ft : flights){
                        if (ft[0] == flight[1]){
                            queue.offer(ft);
                            prices.offer(price+flight[2]);
                        }
                    }
                }
            }
            depth++;
        }
    }


    /**
     * Accepted
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     */
    public int findCheapestPrice3(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> map = new LinkedHashMap<>();
        for (int[] flight : flights){
            map.put(flight[0], map.getOrDefault(flight[0], new LinkedList<>()));
            map.get(flight[0]).add(flight);
        }
        if (map.containsKey(src)){
            for (int[] flight : map.get(src)){
                dfs(n, map, flight, dst, K, 0, flight[2]);
            }
        }
        return minPrice==Integer.MAX_VALUE?-1:minPrice;
    }


    private void dfs(int n,  Map<Integer, List<int[]>> flights, int[] src, int dst, int K, int depth, int price){
        if (depth > K){
            return;
        }
        if (price>minPrice){
            return;
        }
        if (src[1] == dst){
            minPrice = Math.min(minPrice, price);
            return;
        }
        if (flights.containsKey(src[1])){
            for (int[] fs : flights.get(src[1])){
                dfs(n, flights, fs, dst, K, depth+1, price+fs[2]);
            }
        }
    }


    public int findCheapestPrice4(int n, int[][] flights, int src, int dst, int K) {

        return minPrice==Integer.MAX_VALUE?-1:minPrice;
    }
}
