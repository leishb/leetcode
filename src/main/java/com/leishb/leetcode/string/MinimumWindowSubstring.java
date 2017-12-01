package com.leishb.leetcode.string;

import com.leishb.leetcode.tag.TwoPointers;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by me on 2017/11/28.
 */
@TwoPointers
public class MinimumWindowSubstring {

    @Test
    public void test(){
        Assert.assertTrue(minWindow("ADOBECODEBANC", "ABC").equals("BANC"));
        Assert.assertTrue(minWindow("ABC", "ABC").equals("ABC"));
        Assert.assertTrue(minWindow("ZTYBC", "ABC").equals(""));
        Assert.assertTrue(minWindow("aa", "aa").equals("aa"));


        Assert.assertTrue(minWindow2("ADOBECODEBANC", "ABC").equals("BANC"));
        Assert.assertTrue(minWindow2("ABC", "ABC").equals("ABC"));
        Assert.assertTrue(minWindow2("ZTYBC", "ABC").equals(""));
        Assert.assertTrue(minWindow2("aa", "aa").equals("aa"));
    }


    /**
     * Accepted
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length()<t.length()){
            return "";
        }
        Map<Character, Integer> m1 = new HashMap<>();
        Map<Character, Integer> m2 = new HashMap<>();
        for (int i=0;i<t.length();i++){
            m1.put(t.charAt(i), m1.getOrDefault(t.charAt(i), 0)+1);
        }
        int i=0;
        int min = Integer.MAX_VALUE;
        String ans = "";
        for (int j=0;j<s.length();j++){
            char c = s.charAt(j);
            if (m1.containsKey(c)){
                int count = m2.getOrDefault(c, 0);
                m2.put(c, count+1);
            }
            while (m1.size()==m2.size() && mapEquals(m1, m2)){
                if (j-i+1<min){
                    min = j-i+1;
                    ans = s.substring(i, j+1);
                }
                if (m1.containsKey(s.charAt(i))){
                    int count = m2.get(s.charAt(i));
                    m2.put(s.charAt(i), count-1);
                }
                i++;
            }
        }
        return ans;
    }

    private boolean mapEquals(Map<Character, Integer> m1, Map<Character, Integer> m2){
        for (Character c : m1.keySet()){
            int count = m1.get(c);
            if (!m2.containsKey(c) || m2.get(c) < count){
                return false;
            }
        }
        return true;
    }


    /**
     * Accepted
     * @param s
     * @param t
     * @return
     */
    public String minWindow2(String s, String t) {
        String ans = "";
        Map<Character, Integer> m1 = new HashMap<>();
        for (int i=0;i<t.length();i++){
            m1.put(t.charAt(i), m1.getOrDefault(t.charAt(i), 0)+1);
        }
        int i=0;
        int min = Integer.MAX_VALUE;
        int count = t.length();
        for (int j=0;j<s.length();j++) {
            char c = s.charAt(j);
            if (m1.containsKey(c)){
                if (m1.get(c)>0){
                    count--;
                }
                m1.put(c, m1.get(c)-1);
            }
            while (count==0){//move left pointer
                if (j-i+1<min){
                    min = j-i+1;
                    ans = s.substring(i, j+1);
                }
                if (!m1.containsKey(s.charAt(i))){
                    i++;
                }else if (m1.get(s.charAt(i)) <0){
                    m1.put(s.charAt(i), m1.get(s.charAt(i))+1);
                    i++;
                }else if (m1.get(c) >=0){
                    count++;
                    m1.put(s.charAt(i), m1.get(s.charAt(i))+1);
                    i++;
                }
            }
        }
        return ans;
    }

}
