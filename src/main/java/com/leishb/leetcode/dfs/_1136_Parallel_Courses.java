package com.leishb.leetcode.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by me on 2019/12/30.
 */
public class _1136_Parallel_Courses {


    /**
     * Accepted
     * @param N
     * @param relations
     * @return
     */
    public int minimumSemesters(int N, int[][] relations) {
        int[] inDegree = new int[N+1];
        List<Integer>[] adj = new List[N+1];
        for (int i=0;i<=N;i++) adj[i] = new ArrayList<>();
        for (int[] r : relations){
            inDegree[r[1]]++;
            adj[r[0]].add(r[1]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i=1;i<=N;i++){
            if (inDegree[i]==0) queue.offer(i);
        }
        int ans = 0, count = 0;
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-->0){
                int cur = queue.poll();
                count++;
                for (int next : adj[cur]){
                    inDegree[next]--;
                    if (inDegree[next]==0){
                        queue.offer(next);
                    }
                }
            }
            ans++;
        }
        return count==N ? ans : -1;
    }
}
