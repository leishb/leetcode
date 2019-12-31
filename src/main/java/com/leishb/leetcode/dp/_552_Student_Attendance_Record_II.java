package com.leishb.leetcode.dp;

/**
 * Created by me on 2019/11/26.
 */
public class _552_Student_Attendance_Record_II {


    /**
     * Accepted
     * @param n
     * @return
     */
    public int checkRecord(int n) {
        int[][][] dp = new int[n+1][2][3];
        return dfs(n, 0, 0, dp);
    }


    int M = 1_000_000_007;


    private int dfs(int n, int absents, int later,int[][][] dp){
        if (n==0){
            return 1;
        }
        if (dp[n][absents][later]!=0) {
            return dp[n][absents][later];
        }
        int ans = 0;
        ans = (ans + dfs(n-1, absents, 0, dp)%M)%M;//P
        if (absents==0){
            ans = (ans + dfs(n-1, 1, 0, dp)%M)%M;//A
        }
        if (later<2){
            ans = (ans + dfs(n-1, absents, later+1, dp)%M)%M;//L
        }
        dp[n][absents][later] = ans%M;
        return ans;
    }


    public int checkRecord2(int n) {
        long[] dp = new long[n+1];
        for (int i=0;i<=n;i++){
            dp[i] = fun(i, dp);
        }
        long ans = dp[n];
        for (int i=1;i<=n;i++){
            ans = (ans%M + (dp[i-1] * dp[n-i]) %M)%M;
        }
        return (int) ans;
    }


    private long fun(int i, long[] dp){
        if (i==0) return 1;
        if (i==1) return 2;
        if (i==2) return 4;
        if (i==3) return 7;
        if (dp[i]!=0) return dp[i];
        dp[i] = ((2 * fun(i-1, dp))%M - fun(i-4, dp)%M)%M;
        return dp[i];
    }

}
