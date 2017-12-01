package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.SlidingWindow;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by me on 2017/12/1.
 */
@SlidingWindow
public class LongestSubString {


    @Test
    public void test(){
        Assert.assertTrue(lengthOfLongestSubstring("pwwkew")==3);
        Assert.assertTrue(lengthOfLongestSubstring("pwwkepw")==4);

        Assert.assertTrue(lengthOfLongestSubstring2("pwwkew")==3);
        Assert.assertTrue(lengthOfLongestSubstring2("pwwkepw")==4);
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int max = 0;
        Map<Character, Integer> map = new HashMap<>();
        int i=0;
        for(int j=0;j<s.length();j++){
            if(!map.containsKey(s.charAt(j)) || map.get(s.charAt(j)) < i){
                max = Math.max(j-i+1, max);
            }else{
                i = map.get(s.charAt(j))+1;
            }
            map.put(s.charAt(j), j);
        }
        return max;
    }


    /**
     * Accepted
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int max=0;
        Set<Character> set = new HashSet<>();
        int i=0;
        for (int j=0;j<s.length();j++){
            char c = s.charAt(j);
            while (set.contains(c)){
                set.remove(s.charAt(i));
                i++;
            }
            max = Math.max(max, j-i+1);
            set.add(c);
        }
        return max;
    }
}
