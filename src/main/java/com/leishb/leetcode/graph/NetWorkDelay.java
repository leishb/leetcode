package com.leishb.leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/3/12.
 */
public class NetWorkDelay {


    class Edge{
        private int sid;
        private int tid;
        private int w;

        public Edge(int sid, int tid, int w){
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }


    class Vertex implements Comparable<Vertex>{
        private int id;
        private int dist;

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.dist, o.dist);
        }
    }


    public int networkDelayTime(int[][] times, int N, int K) {
        int delay = 0;
        List<Edge>[] adj = new LinkedList[N+1];
        for(int i=0;i<N+1;i++){
            adj[i] = new LinkedList();
        }
        for(int[] time : times){
            adj[time[0]].add(new Edge(time[0], time[1], time[2]));
        }

        boolean[] visited = new boolean[N+1];
        Queue<Integer> queue = new LinkedList();
        queue.offer(K);
        visited[K] =true;
        int count=0;

        while(!queue.isEmpty()){
            int i = queue.poll();
            count++;
            for(Edge e : adj[i]){
                if (!visited[e.tid]){
                    delay+=e.w;
                    queue.offer(e.tid);
                    visited[e.tid]=true;
                }
            }
        }

        if (count==N){
            return delay;
        }
        return -1;
    }


    /**
     * Accepted
     * @param times
     * @param N
     * @param K
     * @return
     */
    public int networkDelayTime2(int[][] times, int N, int K) {
        int delay = 0;
        List<Edge>[] adj = new LinkedList[N+1];
        Vertex[] vertices = new Vertex[N+1];
        for(int i=0;i<N+1;i++){
            adj[i] = new LinkedList();
            vertices[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        for(int[] time : times){
            adj[time[0]].add(new Edge(time[0], time[1], time[2]));
        }
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        boolean[] inqueue = new boolean[N+1];
        queue.offer(vertices[K]);
        inqueue[K] = true;
        vertices[K].dist = 0;
        while (!queue.isEmpty()){
            Vertex minVertex = queue.poll();
            for (Edge e : adj[minVertex.id]){
                if (e.w + minVertex.dist < vertices[e.tid].dist){
                    vertices[e.tid].dist = e.w + minVertex.dist;
                    if (inqueue[e.tid]){
                        queue.remove(vertices[e.tid]);
                        queue.offer(vertices[e.tid]);
                    }else {
                        queue.offer(vertices[e.tid]);
                        inqueue[e.tid] = true;
                    }
                }
            }
        }
        for (int i=1;i<N+1;i++){
            if (!inqueue[i]){
                return -1;
            }
        }
        for (int i=1;i<N+1;i++){
            delay = Math.max(delay, vertices[i].dist);
        }
        return delay;
    }
}
