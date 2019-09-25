package com.leishb.leetcode.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/9/24.
 */
public class _340_Longest_Substring_with_At_Most_K_Distinct_Characters {

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> freq = new HashMap<>();
        int i=0;
        int ans = 0;
        for (int j=0;j<s.length();j++){
            freq.put(s.charAt(j), freq.getOrDefault(s.charAt(j), 0)+1);
            while (freq.size() > k && i<=j){
                freq.put(s.charAt(i), freq.getOrDefault(s.charAt(i), 0)-1);
                if (freq.get(s.charAt(i))==0) freq.remove(s.charAt(i));
                i++;
            }
            ans = Math.max(ans, j-i+1);
        }
        return ans;
    }
}
