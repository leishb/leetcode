package com.leishb.leetcode.slidewindow;

/**
 * Created by me on 2019/9/10.
 */
public class _1004_Max_Consecutive_Ones_III {

    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int longestOnes(int[] A, int K) {
        int count = 0;
        int start = 0;
        int max = 0;
        for (int end=0;end<A.length;end++){
            if (A[end]==0) count+=1;
            while (start< A.length && count > K){
                if (A[start]==0)count-=1;
                start++;
            }
            max = Math.max(max, end-start+1);
        }
        return max;
    }
}
