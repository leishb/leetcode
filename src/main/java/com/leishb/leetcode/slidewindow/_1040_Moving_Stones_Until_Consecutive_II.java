package com.leishb.leetcode.slidewindow;

import java.util.Arrays;

/**
 * Created by me on 2020/3/25.
 */
public class _1040_Moving_Stones_Until_Consecutive_II {


    /**
     * Accepted
     * @param A
     * @return
     */
    public int[] numMovesStonesII(int[] A) {
        Arrays.sort(A);
        int n = A.length;
        int high = Math.max(A[n-1] - A[1] - n + 2, A[n-2] - A[0] - n + 2);
        int j = 0, low = n;
        for(int i=0;i<n;i++){
            while(A[i] - A[j] + 1 > n) j++;
            int stored = i - j + 1;
            if(stored == n-1 && A[i] - A[j] + 1 == n - 1){
                low = Math.min(low, 2);
            }else {
                low = Math.min(low, n - stored);
            }
        }
        return new int[]{low, high};
    }
}
