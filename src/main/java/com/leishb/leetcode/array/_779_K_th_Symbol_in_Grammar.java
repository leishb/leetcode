package com.leishb.leetcode.array;

/**
 * Created by me on 2019/8/12.
 */
public class _779_K_th_Symbol_in_Grammar {

    /**
     * Accepted
     * @param N
     * @param K
     * @return
     */
    public int kthGrammar(int N, int K) {
        if (N==1)return 0;
        if (K<=Math.pow(2, N-2)){
            return kthGrammar(N-1,K);
        }
        return kthGrammar(N-1,K-(int)Math.pow(2, N-2))^1;
    }
}
