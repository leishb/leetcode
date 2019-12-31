package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/12/31.
 */
public class _936_Stamping_The_Sequence {


    /**
     * Accepted
     * @param stamp
     * @param target
     * @return
     */
    public int[] movesToStamp(String stamp, String target) {
        char[] T = target.toCharArray();
        char[] S = stamp.toCharArray();
        List<Integer> list = new ArrayList<>();
        int stars = 0;
        boolean[] visited = new boolean[T.length];
        while (stars < T.length){
            int temp = stars;
            for (int i=0;i<=T.length-S.length;i++){
                if (!visited[i] && canReplace(T, S, i)){
                    stars += replace(T, S, i);
                    list.add(i);
                    visited[i] = true;
                }
                if (stars==T.length){
                    break;
                }
            }
            if (stars==temp){
                return new int[0];
            }
        }
        int[] res = new int[list.size()];
        for (int i=list.size()-1;i>=0;i--){
            res[list.size()-1-i] = list.get(i);
        }
        return res;
    }


    private boolean canReplace(char[] T, char[] S, int start){
        for (int i=0;i<S.length;i++){
            if (T[i+start] != '*' && T[i+start] != S[i]){
                return false;
            }
        }
        return true;
    }


    private int replace(char[] T, char[] S, int start){
        int count = 0;
        for (int i=0;i<S.length;i++){
            if (T[i+start] != '*'){
                T[i+start] = '*';
                count++;
            }
        }
        return count;
    }
}
