package com.leishb.leetcode.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by me on 2020/1/2.
 */
public class _757_Set_Intersection_Size_At_Least_Two {


    /**
     * Accepted
     * @param intervals
     * @return
     */
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (t1, t2)->t1[0]==t2[0]?t2[1]-t1[1]:t1[0]-t2[0]);
        Stack<int[]> stack = new Stack<>();
        for (int[] t : intervals){
            while (!stack.isEmpty() && stack.peek()[1]>=t[1]) stack.pop();
            stack.push(t);
        }
        int[][] arr = new int[stack.size()][2];
        for (int i=arr.length-1;i>=0;i--){
            arr[i] = stack.pop();
        }
        int p1 = arr[0][1]-1;
        int p2 = arr[0][1];
        int ans = 2;
        for (int i=1;i<arr.length;i++){
            boolean choose1 = arr[i][0]<=p1 && arr[i][1]>=p1;
            boolean choose2 = arr[i][0]<=p2 && arr[i][1]>=p2;
            if (choose1 && choose2) continue;
            if (choose2){
                p1 = p2;
                p2 = arr[i][1];
                ans++;
            }else {
                p1 = arr[i][1]-1;
                p2 = arr[i][1];
                ans +=2;
            }
        }
        return ans;
    }
}
