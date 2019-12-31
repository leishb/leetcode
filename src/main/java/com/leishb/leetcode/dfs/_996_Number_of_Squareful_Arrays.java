package com.leishb.leetcode.dfs;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by me on 2019/12/4.
 */
public class _996_Number_of_Squareful_Arrays {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int numSquarefulPerms(int[] A) {
        Map<Integer, Set<Integer>> squares = new HashMap<>();
        Map<Integer, Integer> freq = new HashMap<>();
        squares.put(-1, new HashSet<>());
        for (int i=0;i<A.length;i++){
            freq.put(A[i], freq.getOrDefault(A[i], 0)+1);
            squares.putIfAbsent(A[i], new HashSet<>());
            squares.get(-1).add(A[i]);
            for (int j=i+1;j<A.length;j++){
                squares.putIfAbsent(A[j], new HashSet<>());
                if (isSquare(A[i] + A[j])){
                    squares.get(A[i]).add(A[j]);
                    squares.get(A[j]).add(A[i]);
                }
            }
        }
        return backtracking(squares, freq, -1, 0, A.length);
    }

    private int backtracking(Map<Integer, Set<Integer>> squares, Map<Integer, Integer> freq , int last, int size, int len){
        if (size==len){
            return 1;
        }
        int ans = 0;
        for (int next : squares.get(last)){
            if (freq.get(next)==0) continue;
            freq.put(next, freq.get(next)-1);
            ans += backtracking(squares, freq, next, size+1, len);
            freq.put(next, freq.get(next)+1);
        }
        return ans;
    }

    private boolean isSquare(int num){
        int d = (int) Math.sqrt(num);
        return d * d == num;
    }


    @Test
    public void test(){
        numSquarefulPerms(new int[]{1,1});
    }
}
