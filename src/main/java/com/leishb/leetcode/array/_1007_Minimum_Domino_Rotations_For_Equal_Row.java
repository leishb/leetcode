package com.leishb.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/12/25.
 */
public class _1007_Minimum_Domino_Rotations_For_Equal_Row {


    /**
     * Accepted
     * @param A
     * @param B
     * @return
     */
    public int minDominoRotations(int[] A, int[] B) {
        int[] counts = new int[7];
        for (int i=0;i<A.length;i++){
            counts[A[i]]++;
            counts[B[i]]++;
        }
        List<Integer> majors = new ArrayList<>();
        for (int i=0;i<7;i++){
            if (counts[i]>=A.length)majors.add(i);
        }
        int ans = Integer.MAX_VALUE;
        for (int m : majors){
            ans = Math.min(helper(A, B, m), ans);
        }
        return ans == Integer.MAX_VALUE  ? -1 : ans;
    }


    private int helper(int[] A, int[] B, int major){
        int ansA=0, ansB = 0;
        for (int i=0;i<A.length;i++){
            if (A[i]!=major && B[i]!=major) return Integer.MAX_VALUE;
            if (A[i]!=major) ansA++;
            if (B[i]!=major) ansB++;
        }
        return Math.min(ansA, ansB);
    }
}
