package com.leishb.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2020/1/3.
 */
public class _932_Beautiful_Array {


    Map<Integer, int[]> memo = new HashMap<>();


    /**
     * Accepted
     * @param N
     * @return
     */
    public int[] beautifulArray(int N) {
        return f(N);
    }


    private int[] f(int N){
        if (memo.containsKey(N)) return memo.get(N);
        if (N==1){
            return new int[]{1};
        }
        int[] ans = new int[N];
        int i = 0;
        for (int x : f((N+1)/2)){
            ans[i++] = 2*x-1;
        }
        for (int x : f(N/2)){
            ans[i++] = 2*x;
        }
        memo.put(N, ans);
        return ans;
    }
}
