package com.leishb.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by me on 2019/10/9.
 */
public class _277_Find_the_Celebrity {

    /**
     * Accepted
     * @param n
     * @return
     */
    public int findCelebrity(int n) {
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<n;i++){
            if (set.contains(i))continue;
            int j=0;
            for (;j<n;j++){
                if (i==j)continue;
                if (knows(j, i)){
                    set.add(j);
                    if (knows(i, j)) break;
                }else {
                    break;
                }
            }
            if (j==n) return i;
        }
        return -1;
    }

    public int findCelebrity2(int n) {
        int candidate = 0;
        for (int i=1;i<n;i++){
            if (knows(candidate, i)){
                candidate = i;
            }
        }
        for (int i=0;i<n;i++){
            if (i==candidate)continue;
            if (knows(candidate, i) || !knows(i, candidate)) return -1;
        }
        return candidate;
    }
    boolean knows(int a, int b){
        return true;
    }
}
