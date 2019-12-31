package com.leishb.leetcode.dfs;

import java.util.*;

/**
 * Created by me on 2019/12/4.
 */
public class _207_Course_Schedule {


    /**
     * Accepted
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        for (int i=0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        for (int[] prepare : prerequisites){
            graph[prepare[0]].add(prepare[1]);
        }
        boolean[] visited = new boolean[numCourses];
        for (int i=0;i<numCourses;i++){
            if (visited[i]) continue;
            if (!dfs(i, graph, visited, new boolean[numCourses])){
                return false;
            }
        }
        return true;
    }


    private boolean dfs(int cur, List<Integer>[] graph, boolean[] visited, boolean[] onstack){
        visited[cur] = true;
        onstack[cur] = true;
        for (int next : graph[cur]){
            if (onstack[next]) return false;
            if (visited[next])continue;
            if (!dfs(next, graph, visited, onstack)){
                return false;
            }
        }
        onstack[cur] = false;
        return true;
    }

    /**
     * Accepted
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        List<Integer>[] graph = new List[numCourses];
        for (int i=0;i<numCourses;i++){
            graph[i] = new ArrayList<>();
        }
        int[] inDegree = new int[numCourses];
        for (int[] prepare : prerequisites){
            graph[prepare[1]].add(prepare[0]);
            inDegree[prepare[0]]++;
        }
        int  k = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0;i<numCourses;i++){
            if (inDegree[i]==0){
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()){
            int cur = queue.poll();
            ans[k++] = cur;
            for (int next : graph[cur]){
                inDegree[next]--;
                if (inDegree[next]==0){
                    queue.offer(next);
                }
            }
        }
        return k==numCourses ? ans : new int[0];
    }
}
