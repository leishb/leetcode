package com.leishb.leetcode.bfs;

import java.util.*;

/**
 * Created by me on 2019/11/20.
 */
public class _847_Shortest_Path_Visiting_All_Nodes {


    /**
     * Accepted
     * @param graph
     * @return
     */
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        for (int i=0;i<n;i++){
            State state = new State(1<<i, i, 0);
            queue.offer(state);
            visited.add(state.toString());
        }

        while (!queue.isEmpty()){
            State curState = queue.poll();
            if (curState.mask == (1<<n)-1){
                return curState.cost;
            }
            for (int next : graph[curState.cur]){
                State state = new State(curState.mask|(1<<next) , next, curState.cost+1);
                if (!visited.contains(state.toString())){
                    queue.offer(state);
                    visited.add(state.toString());
                }
            }
        }
        return 0;
    }


    /**
     * Accepted
     * @param graph
     * @return
     */
    public int shortestPathLength2(int[][] graph) {
        int n = graph.length;
        Queue<State> queue = new LinkedList<>();
        int[][] dist = new int[n][1<<n];
        for (int i=0;i<n;i++){
            Arrays.fill(dist[i], Integer.MAX_VALUE);
            State state = new State(1<<i, i, 0);
            dist[state.cur][state.mask] = 0;
            queue.offer(state);
        }
        while (!queue.isEmpty()){
            State cs = queue.poll();
            if (cs.mask == (1<<n)-1){
                return cs.cost;
            }
            for (int next : graph[cs.cur]){
                State state = new State(cs.mask|(1<<next) , next, cs.cost+1);
                if (dist[state.cur][state.mask] > state.cost){
                    queue.offer(state);
                    dist[state.cur][state.mask] = state.cost;
                }
            }
        }
        return 0;
    }

    class State{
        int mask;
        int cur;
        int cost;

        State(int mask, int cur, int cost){
            this.mask = mask;
            this.cur = cur;
            this.cost = cost;
        }

        public String toString(){
            return mask + "-" + cur + "-" + cost;
        }
    }
}
