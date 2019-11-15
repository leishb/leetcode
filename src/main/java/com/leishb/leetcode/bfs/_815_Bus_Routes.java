package com.leishb.leetcode.bfs;

import java.util.*;

/**
 * Created by me on 2019/11/12.
 */
public class _815_Bus_Routes {


    /**
     * Accepted
     * @param routes
     * @param S
     * @param T
     * @return
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S==T) return 0;
        Set<Integer>[] sets = new Set[routes.length];
        for (int i=0;i<sets.length;i++){
            sets[i] = new HashSet<>();
            for (int j=0;j<routes[i].length;j++){
                sets[i].add(routes[i][j]);
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[routes.length];
        for (int i=0;i<sets.length;i++){
            if (sets[i].contains(S)){
                queue.offer(i);
                visited[i] = true;
            }
        }
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int cur = queue.poll();
                if (sets[cur].contains(T)) return step;
                for (int i=0;i<sets.length;i++){
                    if (visited[i]) continue;
                    for (int j : sets[cur]){
                        if (sets[i].contains(j)){
                            queue.offer(i);
                            visited[i] = true;
                            break;
                        }
                    }
                }
            }
            step++;
        }
        return -1;
    }


    public int numBusesToDestination2(int[][] routes, int S, int T) {
        if (S == T) return 0;
        List<Integer>[] adj = new List[routes.length];
        for (int i=0;i<routes.length;i++){
            adj[i] = new ArrayList<>();
            Arrays.sort(routes[i]);
        }
        for (int i=0;i<routes.length;i++){
            for (int j=i+1;j<routes.length;j++){
                if (intersect(routes[i], routes[j])){
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        Set<Integer> targets = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[routes.length];
        for (int i=0;i<routes.length;i++){
            if (Arrays.binarySearch(routes[i], S) >=0){
                queue.offer(i);
                visited[i] = true;
            }
            if (Arrays.binarySearch(routes[i], T)>=0){
                targets.add(i);
            }
        }
        int step = 1;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int cur = queue.poll();
                if (targets.contains(cur)) return step;
                for (int next : adj[cur]){
                    if (visited[next]) continue;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
            step++;
        }
        return -1;
    }

    private boolean intersect(int[] A, int[] B){
        int i=0,j=0;
        while (i<A.length && j<B.length){
            if (A[i]==B[j]) return true;
            if (A[i]<A[j]) i++;
            else j++;
        }
        return false;
    }


}
