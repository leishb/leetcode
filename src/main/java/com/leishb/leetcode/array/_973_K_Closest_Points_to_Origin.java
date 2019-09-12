package com.leishb.leetcode.array;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by me on 2019/9/6.
 */
public class _973_K_Closest_Points_to_Origin {

    public int[][] kClosest(int[][] points, int K) {
        Queue<int[]> pq = new PriorityQueue<>((p1, p2)-> ((p1[0]*p1[0]+p1[1]*p1[1])-(p2[0]*p2[0]+p2[1]*p2[1])));
        for (int[] point : points){
            pq.offer(point);
        }
        int[][] ans = new int[K][2];
        int i = 0;
        while (K-->0){
            ans[i++] = pq.poll();
        }
        return ans;
    }
}
