package com.leishb.leetcode.favorite;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Created by me on 2019/9/16.
 */
public class _1094_Car_Pooling {

    public boolean carPooling(int[][] trips, int capacity) {
        int end = 0;
        for (int[] trip : trips){
            end = Math.max(end, trip[2]);
        }
        Arrays.sort(trips, (t1, t2)->t1[1]-t2[1]);
        int start = trips[0][1];
        for (int i=start;i<end;i++){
            int cur = 0;
            for (int[] trip : trips){
                if (trip[1]<=i && trip[2]>i){
                    cur+=trip[0];
                }else if (trip[1] > i){
                    break;
                }
            }
            if (cur>capacity) return false;
        }
        return true;
    }

    public boolean carPooling2(int[][] trips, int capacity) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] trip : trips){
            map.put(trip[1], map.getOrDefault(trip[1], 0)+trip[0]);
            map.put(trip[2], map.getOrDefault(trip[2], 0)-trip[0]);
        }
        for (int k : map.keySet()){
            capacity-=map.get(k);
            if (capacity<0)return false;
        }
        return true;
    }


    public boolean carPooling3(int[][] trips, int capacity) {
        Arrays.sort(trips, (t1, t2)->t1[1]-t2[1]);
        Queue<Integer> pq = new PriorityQueue<>((a, b)->trips[a][2]-trips[b][2]);
        int persons = 0;
        for (int i=0;i<trips.length ;i++ ) {
            while (!pq.isEmpty() && trips[i][1]>=trips[pq.peek()][2]) {
                persons -= trips[pq.poll()][0];
            }
            persons+=trips[i][0];
            pq.offer(i);
            if (persons > capacity) {
                return false;
            }
        }
        return true;
    }


    @Test
    public void test(){
        Assert.assertFalse(carPooling2(new int[][]{{8,2,3},{4,1,3}, {1,3,6}, {8,4,6}, {4,4,8}}, 12));
    }
}
