package com.leishb.leetcode.array;

/**
 * Created by me on 2019/12/9.
 */
public class _995_Minimum_Number_of_K_Consecutive_Bit_Flips {


    /**
     * Accepted
     * @param A
     * @param K
     * @return
     */
    public int minKBitFlips(int[] A, int K) {
        int ans = 0;
        for (int i=0;i<A.length;i++){
            if (A[i]==0){
                if (i+K>A.length) return -1;
                flip(A, i, K);
                ans++;
            }
        }
        return ans;
    }


    private void flip(int[] A, int i, int K){
        for (int j=i;j<i+K;j++){
            A[j]^=1;
        }
    }



}
