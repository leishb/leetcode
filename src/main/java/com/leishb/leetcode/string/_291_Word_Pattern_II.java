package com.leishb.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2019/10/29.
 */
public class _291_Word_Pattern_II {


    /**
     * Accepted
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPatternMatch(String pattern, String str) {
        if (pattern.length()>str.length()) return false;
        return backtracking(pattern, 0, str, 0, new HashMap<>());
    }



    private boolean backtracking(String pattern, int p, String str, int s, Map<Character, String> map){
        if (pattern.length()-p > str.length()-s) return false;
        if (p==pattern.length()){
            return  s==str.length();
        }
        char c = pattern.charAt(p);
        String val = map.get(c);
        if (val!=null){
            return s+val.length() <= str.length() &&
                    str.substring(s, s+val.length()).equals(val) &&
                    backtracking(pattern, p+1, str, s+val.length(), map);
        }
        for (int i=s+1;i<=str.length();i++){
            String sub = str.substring(s, i);
            if (map.values().contains(sub))continue;
            map.put(c, sub);
            if (backtracking(pattern, p+1, str, i, map)){
                return true;
            }
            map.remove(c, sub);
        }
        return false;
    }


    @Test
    public void test(){
        Assert.assertFalse(wordPatternMatch("itwasthebestoftimes", "ittwaastthhebesttoofttimesss"));
    }
}
