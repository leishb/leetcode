package com.leishb.leetcode.array;

import java.util.Stack;

/**
 * Created by me on 2019/8/6.
 */
public class _739_Daily_Temperatures {

    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        for (int i=0;i<T.length;i++){
            int j = i+1;
            for (;j<T.length;j++){
                if (T[j] > T[i]){
                    break;
                }
            }
            if (j<T.length){
                ans[i] = j-i;
            }else {
                ans[i] = 0;
            }
        }
        return ans;
    }

    public int[] dailyTemperatures2(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[T.length];
        for (int i=T.length-1;i>=0;i--){
            while (!stack.isEmpty() && T[i] >= T[stack.peek()]){
                stack.pop();
            }
            ans[i] = stack.isEmpty()?0:stack.peek()-i;
            stack.push(i);
        }
        return ans;
    }
}