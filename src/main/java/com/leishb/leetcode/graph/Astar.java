package com.leishb.leetcode.graph;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by me on 2019/3/14.
 */
public class Astar {


    private int v;

    private List<Edge>[] adj ;

    private Vertex[] vertexes;

    public Astar(int v){
        this.v = v;
        this.adj = new LinkedList[v];
        vertexes = new Vertex[v];
        for (int i=0;i<v;i++){
            adj[i] = new LinkedList<>();
        }
    }

    public void addVertex(int id, int x, int y){
        vertexes[id] = new Vertex(id, x, y);
    }

    class Edge{
        private int sid;
        private int tid;
        private int w;

        public Edge(int sid, int tid, int w) {
            this.sid = sid;
            this.tid = tid;
            this.w = w;
        }
    }

    class Vertex {
        private int id;
        private int dist;
        private int x;
        private int y;
        private int f;

        public Vertex(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.dist = Integer.MAX_VALUE;
            this.f = Integer.MAX_VALUE;
        }
    }


    public int astar(int s, int t){
        PriorityQueue<Vertex> queue = new PriorityQueue<>((Comparator<Vertex>) (o1, o2) -> Integer.compare(o1.f, o2.f));
        boolean[] inqueue = new boolean[v];
        vertexes[s].dist = 0;
        vertexes[s].f = 0;
        queue.offer(vertexes[s]);
        inqueue[s] = true;
        int[] path = new int[v];
        while (!queue.isEmpty()){
            Vertex minVertex = queue.poll();
            for (Edge edge : adj[minVertex.id]){
                Vertex next = vertexes[edge.tid];
                if (next.dist > minVertex.dist + edge.w){
                    next.dist = minVertex.dist + edge.w;
                    next.f = next.dist + Math.abs(next.x-vertexes[t].x) + Math.abs(next.y-vertexes[t].y);
                    path[next.id] = minVertex.id;
                    if (inqueue[next.id]){
                        queue.remove(next);
                        queue.offer(next);
                    }else {
                        queue.offer(next);
                        inqueue[next.id] = true;
                    }

                    if (next.id == t){
                        queue.clear();
                        break;
                    }
                }
            }
        }

        return vertexes[t].dist;
    }
}
