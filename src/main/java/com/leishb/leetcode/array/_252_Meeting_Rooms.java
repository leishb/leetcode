package com.leishb.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Created by me on 2019/9/23.
 */
public class _252_Meeting_Rooms {

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2)->i1[0]-i2[0]);
        for (int i=1;i<intervals.length;i++){
            if (intervals[i][0] < intervals[i-1][1]){
                return false;
            }
        }
        return true;
    }



    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2)->i1[0]-i2[0]);
        TreeMap<int[], Integer> map = new TreeMap<>((a1, a2)->a1[0]==a2[0]?a1[1]-a2[1]:a1[0]-a2[0]);
        int ans = 0;
        for (int i=0;i<intervals.length;i++){
            map.put(intervals[i], map.getOrDefault(intervals[i], 0)+1);
        }
        for (int i=0;i<intervals.length;i++){
            if (map.containsKey(intervals[i])){
                int[] prev = intervals[i];
                while (prev!=null){
                    map.put(prev, map.get(prev)-1);
                    if (map.get(prev)==0)map.remove(prev);
                    prev = map.ceilingKey(new int[]{prev[1], prev[1]});
                }
                ans+=1;
            }
        }
        return ans;
    }

    public int minMeetingRooms2(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];
        for (int i=0;i<intervals.length;i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int rooms = 0;
        int j=0;
        for (int i=0;i<intervals.length;i++){
            if (start[i]<end[j]){
                rooms++;
            }else {
                j++;
            }
        }
        return rooms;
    }
    public int minMeetingRooms3(int[][] intervals) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i=0;i<intervals.length;i++){
           map.put(intervals[i][0], map.getOrDefault(intervals[i][0],0)+1);
           map.put(intervals[i][1], map.getOrDefault(intervals[i][1],0)-1);
        }
        int ans = 0;
        int rooms = 0;
        for (int v : map.values()){
            rooms +=v;
            ans = Math.max(ans, rooms);
        }
        return ans;
    }

    public int minMeetingRooms4(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2)->i1[0]-i2[0]);
        Queue<int[]> pq = new PriorityQueue<>((i1, i2)->i1[1]-i2[1]);
        int ans = 0;
        for (int i=0;i<intervals.length;i++){
            while (!pq.isEmpty() && intervals[i][0] >= pq.peek()[1]){
                pq.poll();
            }
            pq.offer(intervals[i]);
            ans = Math.max(ans, pq.size());
        }
        return ans;
    }



    @Test
    public void test(){
        Assert.assertTrue(minMeetingRooms4(new int[][]{{1293,2986},{848,3846},{4284,5907},{4466,4781},{518,2918},{300,5870}})==4);
    }
}
