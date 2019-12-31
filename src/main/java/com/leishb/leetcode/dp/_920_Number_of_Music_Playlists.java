package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/11/25.
 */
public class _920_Number_of_Music_Playlists {


    int M = 1_000_000_007;


    /**
     * Accepted
     * @param N
     * @param L
     * @param K
     * @return
     */
    public int numMusicPlaylists(int N, int L, int K) {
        long[][] dp = new long[L+1][N+1];// dp[i][j] : i songs with j different songs
        dp[0][0] = 1;
        for (int i=1;i<=L;i++){
            for (int j=1;j<=N;j++){
                dp[i][j] = (dp[i-1][j-1] * (N-(j-1)))%M;
                if (j>K){
                    dp[i][j] = (dp[i][j] + (dp[i-1][j] * (j-K))%M)%M;
                }
            }
        }
        return (int) dp[L][N];
    }
}
