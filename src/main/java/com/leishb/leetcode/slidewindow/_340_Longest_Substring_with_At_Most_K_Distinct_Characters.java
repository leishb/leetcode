package com.leishb.leetcode.slidewindow;

import java.util.HashMap;
import java.util.LinkedHashMap;
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


    public int lengthOfLongestSubstringKDistinct2(String s, int k) {
        int left = 0, ans = 0;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap();
        for (int i=0;i<s.length() ;i++ ) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.remove(c);
            }
            map.put(c, i);
            if (map.size()>k) {
                char key = map.keySet().iterator().next();
                left = map.get(key)+1;
                map.remove(key);
            }
            ans = Math.max(ans, i-left+1);
        }
        return ans;
    }
}
