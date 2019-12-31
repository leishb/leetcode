package com.leishb.leetcode.slidewindow;

/**
 * Created by me on 2019/11/20.
 */
public class _1208_Get_Equal_Substrings_Within_Budget {


    /**
     * Accepted
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int ans = 0;
        int j = 0;
        for (int i=0;i<s.length();i++){
            maxCost -= Math.abs(s.charAt(i)-t.charAt(i));
            while (maxCost < 0){
                maxCost += Math.abs(s.charAt(j)-t.charAt(j));
                j++;
            }
            ans = Math.max(ans, i-j+1);
        }
        return ans;
    }
}
