package com.leishb.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by me on 2019/10/21.
 */
public class _267_Palindrome_Permutation_II {

    /**
     * Accepted
     * @param s
     * @return
     */
    public List<String> generatePalindromes(String s) {
        List<String> ans = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        String mid = "";
        int count = 0;
        for (char c : map.keySet()){
            if (map.get(c)%2==1){
                count++;
                mid = c+"";
            }
            map.put(c, map.get(c)/2);
        }
        if (count>1) return new ArrayList<>();
        backtracking(mid, map, ans, s.length());
        return ans;
    }


    private void backtracking(String s, Map<Character, Integer> map, List<String> ans, int len){
        if (s.length()==len){
            ans.add(s);
            return;
        }
        for (char c : map.keySet()){
            if (map.get(c)==0) continue;
            map.put(c, map.get(c)-1);
            backtracking(c + s + c, map, ans, len);
            map.put(c, map.get(c)+1);
        }
    }
}
