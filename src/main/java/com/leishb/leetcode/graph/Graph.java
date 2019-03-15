package com.leishb.leetcode.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by me on 2019/3/12.
 */
public class Graph {


    private int v;

    private List<Edge>[] adj;

    public Graph(int v){
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0;i<adj.length;i++){
            adj[i] = new LinkedList<>();
        }
    }


    public void addEdge(int source, int dist, int w){
        adj[source].add(new Edge(source, dist, w));
    }


    public int dijkstra(int s, int t){
        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        int[] processer = new int[v];
        boolean[] inqueue = new boolean[v];
        Vertex[] vertexes = new Vertex[v];
        for (int i=0;i<vertexes.length;i++){
            vertexes[i] = new Vertex(i, Integer.MAX_VALUE);
        }
        queue.add(new Vertex(s, 0));
        inqueue[s] = true;
        int minDist = -1;
        while (!queue.isEmpty()){
            Vertex minVeter = queue.poll();

            System.out.println(minVeter.id + "->" + minVeter.dist);
            if (minVeter.id == t){
                minDist = minVeter.dist;
                break;
            }
            for (Edge edge : adj[minVeter.id]){
                Vertex nextVertext = vertexes[edge.tid];
                if (nextVertext.dist > minVeter.dist + edge.w){
                    nextVertext.dist = minVeter.dist + edge.w;
                    if (inqueue[nextVertext.id]){
                        queue.remove(nextVertext);
                        queue.offer(nextVertext);
                    }else {
                        queue.offer(nextVertext);
                        inqueue[nextVertext.id] = true;
                    }
                    processer[nextVertext.id] = minVeter.id;
                }
            }
        }
        print(s, t, processer);
        return minDist;
    }

    private void print(int s, int t, int[] processer){
        StringBuffer sb = new StringBuffer(t+"->");
        int k = processer[t];
        while (k != s){
            sb.append(k).append("->");
            k = processer[k];
        }
        sb.append(s);
        System.out.println(sb);
    }



    public int dijkstra2(int s, int t){
        int[] distance = new int[v];
        int[] processer = new int[v];
        boolean[] visited = new boolean[v];
        for (int i=0;i<distance.length;i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[s] = 0;
        int minDist;
        while (true){
            int minIndex = selectMinIndex(distance, visited);
            if (minIndex == t){
                minDist = distance[minIndex];
                break;
            }
            for (int i=0;i<adj[minIndex].size();i++){
                Edge e = adj[minIndex].get(i);
                if (distance[minIndex] + e.w < distance[e.tid]){
                    distance[e.tid] = distance[minIndex] + e.w;
                    processer[e.tid] = minIndex;
                }
            }
        }
        print(s, t, processer);
        return minDist;
    }


    private int selectMinIndex(int[] distance, boolean[] visited){
        int minIndex = 0;
        int min = Integer.MAX_VALUE;
        for (int i=0;i<distance.length;i++){
            if (!visited[i] && distance[i] < min){
                minIndex = i;
                min = distance[i];
            }
        }
        visited[minIndex] = true;
        return minIndex;
    }


    class Edge {
        private int sid;
        private int tid;
        private int w;

        public Edge(int source, int dist, int w){
            this.sid = source;
            this.tid = dist;
            this.w = w;
        }
    }


    class Vertex implements Comparable<Vertex>{
        private int dist;

        private int id;

        public Vertex (int s, int dist){
            this.id = s;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return id == vertex.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.dist, o.dist);
        }
    }



    public static void main(String[] args){
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 4, 15);
        graph.addEdge(1, 2, 15);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 5, 5);
        graph.addEdge(3, 2, 1);
        graph.addEdge(3, 5, 12);
        graph.addEdge(4, 5, 10);

        int min = graph.dijkstra(0, 5);
        System.out.println();
        System.out.println(min);


        int min2 = graph.dijkstra2(0, 5);
        System.out.println();
        System.out.println(min2);

    }
}
