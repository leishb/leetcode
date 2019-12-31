package com.leishb.leetcode.dp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by me on 2019/12/24.
 */
public class _514_Freedom_Trail {


    /**
     * Accepted
     * @param ring
     * @param key
     * @return
     */
    public int findRotateSteps(String ring, String key) {
        List<Integer>[] lists = new List[26];
        for (int i=0;i<26;i++){
            lists[i] = new ArrayList<>();
        }
        for (int i=0;i<ring.length();i++){
            lists[ring.charAt(i)-'a'].add(i);
        }
        Integer[][] dp = new Integer[ring.length()][key.length()];
        return dfs(lists, 0, ring, key, 0, dp);
    }



    private int dfs(List<Integer>[] lists, int pos ,String ring,  String key, int index, Integer[][] dp){
        if (index==key.length()) return 0;
        int ans = Integer.MAX_VALUE;
        if (dp[pos][index]!=null) return dp[pos][index];
        for (int next : lists[key.charAt(index)-'a']){
            int step;
            if (next >= pos){
                step = Math.min(next-pos, pos + ring.length()- next);
            }else {
                step = Math.min(pos-next, next + ring.length() - pos);
            }
            ans = Math.min(dfs(lists, next, ring,  key, index+1, dp) + step + 1, ans);
        }
        dp[pos][index] = ans;
        return ans;
    }
}
