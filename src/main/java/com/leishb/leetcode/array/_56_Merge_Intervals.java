package com.leishb.leetcode.array;

import java.util.*;

/**
 * Created by me on 2019/9/25.
 */
public class _56_Merge_Intervals {

    public int[][] merge(int[][] intervals) {
        if (intervals.length==0) return new int[0][0];
        Stack<int[]> stack = new Stack<>();
        Arrays.sort(intervals, (t1, t2)->t1[0]!=t2[0]?t1[0]-t2[0]:t1[1]-t2[1]);
        stack.push(intervals[0]);
        for (int i=1;i<intervals.length;i++){
            int[] cur = intervals[i];
            if (cur[0] > stack.peek()[1]){
                stack.push(cur);
            }else {
                int[] p = stack.pop();
                stack.push(new int[]{p[0], Math.max(p[1], cur[1])});
            }
        }
        int[][] ans = new int[stack.size()][2];
        int k = stack.size()-1;
        while (!stack.isEmpty()){
            ans[k--] = stack.pop();
        }
        return ans;
    }


    /**
     * Accepted
     * @param intervals
     * @return
     */
    public int[][] merge2(int[][] intervals) {
        if (intervals.length == 0) return new int[0][0];
        List<int[]> list = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int[] itv : intervals){
            map.put(itv[0], map.getOrDefault(itv[0], 0)+1);
            map.put(itv[1], map.getOrDefault(itv[1], 0)-1);
        }
        int count = 0, start = Integer.MAX_VALUE, end = Integer.MIN_VALUE;
        for (int k : map.keySet()){
            count+=map.get(k);
            start = Math.min(start, k);
            end = Math.max(end, k);
            if (count==0){
                list.add(new int[]{start, end});
                start = Integer.MAX_VALUE;
                end = Integer.MIN_VALUE;
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}
