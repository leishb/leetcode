package com.leishb.leetcode.array;

import java.util.Arrays;
import java.util.Stack;

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
}
