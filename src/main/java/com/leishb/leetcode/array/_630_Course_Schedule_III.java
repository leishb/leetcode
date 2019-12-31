package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2019/12/4.
 */
public class _630_Course_Schedule_III {


    /**
     * TLE
     * @param courses
     * @return
     */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, (i, j)->i[1]-j[1]);
        return dfs(courses, 0, 0, new HashMap());
    }



    private int dfs(int[][] courses, int day, int index, Map<String, Integer> memo){
        if (index==courses.length){
            return 0;
        }
        String key = day + ","+ index;
        if (memo.containsKey(key)){
            return memo.get(key);
        }
        int[] cur = courses[index];
        int taken = 0;
        if (cur[0] + day<=cur[1]){
            taken = 1+ dfs(courses, cur[0]+day, index+1, memo);
        }
        int notTaken = dfs(courses, day, index+1, memo);
        int max = Math.max(taken, notTaken);
        memo.put(key, max);
        return max;
    }


    public int scheduleCourse2(int[][] courses) {
        Arrays.sort(courses, (i, j)->i[1]-j[1]);
        Queue<Integer> queue = new PriorityQueue<>((a, b)->b-a);
        int time = 0;
        for (int[] cur : courses){
            time+=cur[0];
            queue.offer(cur[0]);
            if (time>cur[1]){
                time-=queue.poll();
            }
        }
        return queue.size();
    }
}
