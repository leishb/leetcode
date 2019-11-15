package com.leishb.leetcode.slidewindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/11/11.
 */
public class _159_Longest_Substring_with_At_Most_Two_Distinct_Characters {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int j = 0;
        int res = 0;
        for(int i=0;i<s.length();i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0)+1);
            while (map.size()>2){
                map.put(s.charAt(j), map.get(s.charAt(j))-1);
                if (map.get(s.charAt(j))==0) map.remove(s.charAt(j));
                j++;
            }
            res = Math.max(res, i-j+1);
        }
        return res;
    }
}
